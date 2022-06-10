package zti.project.f1fantasy.api.raceresult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
    public List<RaceResult> findByDriverId(Long driverId);
    public List<RaceResult> findByRaceId(Long raceId);
}
