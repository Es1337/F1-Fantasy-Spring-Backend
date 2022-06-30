package zti.project.f1fantasy.api.raceresult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
    List<RaceResult> findByDriverId(Long driverId);
    List<RaceResult> findByRaceId(Long raceId);
    List<RaceResult> findByRaceIdAndDriverId(Long raceId, Long driverId);
}
