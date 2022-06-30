package zti.project.f1fantasy.api.reward;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zti.project.f1fantasy.api.availableprediction.AvailablePrediction;
import zti.project.f1fantasy.api.driver.Driver;
import zti.project.f1fantasy.api.driver.DriverService;
import zti.project.f1fantasy.api.race.Race;
import zti.project.f1fantasy.api.race.RaceService;
import zti.project.f1fantasy.api.raceresult.RaceResult;
import zti.project.f1fantasy.api.raceresult.RaceResultService;
import zti.project.f1fantasy.api.ranking.Ranking;
import zti.project.f1fantasy.api.ranking.RankingService;
import zti.project.f1fantasy.api.team.Team;
import zti.project.f1fantasy.api.team.TeamService;
import zti.project.f1fantasy.api.userprediction.UserPrediction;
import zti.project.f1fantasy.api.userprediction.UserPredictionsService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/reward")
public class RewardController {
    private final UserPredictionsService userPredictionsService;
    private final RaceResultService raceResultService;
    private final DriverService driverService;
    private final RaceService raceService;
    private final RankingService rankingService;
    private final TeamService teamService;

    @Autowired
    public RewardController(UserPredictionsService userPredictionsService, RaceResultService raceResultService, DriverService driverService, RaceService raceService, RankingService rankingService, TeamService teamService) {
        this.userPredictionsService = userPredictionsService;
        this.raceResultService = raceResultService;
        this.driverService = driverService;
        this.raceService = raceService;
        this.rankingService = rankingService;
        this.teamService = teamService;
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    @PostMapping(path = "{raceId}")
    public String rewardUsers(@PathVariable Long raceId) {
        String output = "";
        try{
            Race race = raceService.getRaceById(raceId);
            List<UserPrediction> predictionsForRaceList = userPredictionsService.getPredictionsFromRace(raceId);

            for(UserPrediction up : predictionsForRaceList){
                AvailablePrediction ap = up.getAvailablePrediction();
                String code = ap.getTypeCode();
                String title = up.getTitle();

                if(ap.getTarget().equals("D")) {
                    log.info("Exploring Driver target... ");
                    if(code.equals("DRIV1") || code.equals("DRIV2")) {
                        log.info("Found {} code", code);
                        List<Driver> drivers = driverService.getDriversByFullNameFromSeason(title, race.getSeason().getId());
                        if(drivers.size() > 0){
                            log.info("Found {} drivers", drivers.size());
                            Driver driver = drivers.get(0);
                            RaceResult result = raceResultService.getResultsFromRaceForDriver(raceId, driver.getId()).get(0);
                            Ranking userRanking = rankingService.getRankingForUserFromSeason(up.getUser().getId(), race.getSeason().getId());
                            log.info("updatePointsById({}, {}, {})", userRanking.getPoints() + result.getPoints(), up.getUser().getId(), up.getUser().getId());
                            rankingService.updatePointsById(userRanking.getPoints() + result.getPoints(), up.getUser().getId(), race.getSeason().getId());
                        }
                    }
                }
                else {
                    log.info("Exploring Team target... ");
                    if(code.equals("TEAM1")) {
                        log.info("Found {} code", code);
                        Team team = teamService.getTeamByName(title).get(0);
                        List<Driver> drivers = driverService.getDriversFromTeamFromSeason(team.getId(), race.getSeason().getId());
                        while(drivers.size() > 0){
                            log.info("Found {} drivers", drivers.size());
                            Driver driver = drivers.remove(0);
                            RaceResult result = raceResultService.getResultsFromRaceForDriver(raceId, driver.getId()).get(0);
                            Ranking userRanking = rankingService.getRankingForUserFromSeason(up.getUser().getId(), race.getSeason().getId());
                            log.info("updatePointsById({}, {}, {})", userRanking.getPoints() + result.getPoints(), up.getUser().getId(), up.getUser().getId());
                            rankingService.updatePointsById(userRanking.getPoints() + result.getPoints(), up.getUser().getId(), race.getSeason().getId());
                        }
                    }
                }
            }
            output += "Rewards successfully given.";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            output += "Rewards successfully given.";
        }

        return output;
    }
}
