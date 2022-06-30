package zti.project.f1fantasy.api.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.season.SeasonService;
import zti.project.f1fantasy.api.user.User;
import zti.project.f1fantasy.api.user.UserService;

import java.util.List;

@Service
public class RankingService {
    private final RankingRepository rankingRepository;
    private final UserService userService;
    private final SeasonService seasonService;

    @Autowired
    public RankingService(RankingRepository rankingRepository, UserService userService, SeasonService seasonService) {
        this.rankingRepository = rankingRepository;
        this.userService = userService;
        this.seasonService = seasonService;
    }

    public List<Ranking> getAllUserRankings(){
        return rankingRepository.findAll();
    }

    public List<Ranking> getRankingsFromSeason(Long seasonId){
        return rankingRepository.findBySeasonId(seasonId);
    }

    public Ranking getRankingForUserFromSeason(Long userId, Long seasonId){
        return rankingRepository.findByUserIdAndSeasonId(userId, seasonId).orElseThrow(() -> new UserRankingNotFoundException(userId, seasonId));
    }

    public Ranking addRanking(Ranking ranking, Long userId, Long seasonId){
        User user = userService.getUserById(userId);
        Season season = seasonService.getSeasonById(seasonId);
        ranking.setUser(user);
        ranking.setSeason(season);
        return rankingRepository.save(ranking);
    }

    public Ranking updateRankingById(Ranking newRanking, Long userId, Long seasonId){
        Ranking ranking = rankingRepository.findByUserIdAndSeasonId(userId, seasonId).orElseThrow(() -> new UserRankingNotFoundException(seasonId, userId));
        ranking.setPoints(newRanking.getPoints());

        return rankingRepository.save(ranking);
    }

    public Ranking updatePointsById(Integer points, Long userId, Long seasonId){
        Ranking ranking = rankingRepository.findByUserIdAndSeasonId(userId, seasonId).orElseThrow(() -> new UserRankingNotFoundException(seasonId, userId));
        ranking.setPoints(points);

        return rankingRepository.save(ranking);
    }

    public void deleteRankingById(Long userId, Long seasonId){
        Ranking rankingToDelete = rankingRepository.findByUserIdAndSeasonId(userId, seasonId).orElseThrow(() -> new UserRankingNotFoundException(seasonId, userId));
        rankingRepository.delete(rankingToDelete);
    }
}
