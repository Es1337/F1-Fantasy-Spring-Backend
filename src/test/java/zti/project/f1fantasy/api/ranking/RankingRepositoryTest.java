package zti.project.f1fantasy.api.ranking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.season.SeasonService;
import zti.project.f1fantasy.api.user.User;
import zti.project.f1fantasy.api.user.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RankingRepositoryTest {
    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SeasonService seasonService;

    @Test
    public void saveRanking(){
        Long userId = 1L;
        Long seasonId = 1L;

        User user = userService.getUserById(1L);
        Season season = seasonService.getSeasonById(1L);
        Ranking ranking = new Ranking(user, season);
//        Ranking ranking = new Ranking();
//        ranking.setId(key);
        ranking.setPoints(0);
        rankingRepository.save(ranking);

    }
}