package zti.project.f1fantasy.api.availableprediction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailablePredictionRepository extends JpaRepository<AvailablePrediction, Long> {
    public List<AvailablePrediction> findBySeasonId(Long seasonId);
    public List<AvailablePrediction> findByTypeCode(String typeCode);
}
