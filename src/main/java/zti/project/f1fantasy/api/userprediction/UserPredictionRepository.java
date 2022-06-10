package zti.project.f1fantasy.api.userprediction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPredictionRepository extends JpaRepository<UserPrediction, Long> {
    public List<UserPrediction> findByAvailablePredictionIdAndRaceIdAndUserId(Long availablePredictionId, Long raceId, Long userId);
    public List<UserPrediction> findByRaceIdAndUserId(Long raceId, Long userId);
}
