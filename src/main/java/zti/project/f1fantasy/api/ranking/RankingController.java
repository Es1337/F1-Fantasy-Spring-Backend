package zti.project.f1fantasy.api.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/ranking")
public class RankingController {
    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    public List<Ranking> getAllUserRankings(){
        return rankingService.getAllUserRankings();
    }

    @GetMapping(path = "season/{seasonId}")
    public List<Ranking> getRankingsFromSeason(@PathVariable Long seasonId){
        return rankingService.getRankingsFromSeason(seasonId);
    }

    @GetMapping(path = "{seasonId}/{userId}")
    public Ranking getRankingForUserFromSeason(@PathVariable Long seasonId, @PathVariable Long userId){
        return rankingService.getRankingForUserFromSeason(userId, seasonId);
    }

    @PostMapping(path = "{seasonId}/{userId}")
    public Ranking postRanking(@RequestBody Ranking ranking, @PathVariable Long seasonId, @PathVariable Long userId){
        return rankingService.addRanking(ranking, userId, seasonId);
    }

    @PutMapping(path = "{seasonId}/{userId}")
    public Ranking updateRanking(@RequestBody Ranking newRanking, @PathVariable Long seasonId, @PathVariable Long userId){
        return rankingService.updateRankingById(newRanking, userId, seasonId);
    }

    @DeleteMapping(path = "{seasonId}/{userId}")
    public void deleteRanking(@PathVariable Long seasonId, @PathVariable Long userId){
        rankingService.deleteRankingById(userId, seasonId);
    }
}
