package zti.project.f1fantasy.api.ranking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, UserRankingId> {
    List<Ranking> findByUserId(Long userId);
    List<Ranking> findBySeasonId(Long seasonId);
    Optional<Ranking> findByUserIdAndSeasonId(Long userId, Long seasonId);
}
