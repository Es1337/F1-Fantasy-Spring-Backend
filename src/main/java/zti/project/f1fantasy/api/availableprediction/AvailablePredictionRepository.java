package zti.project.f1fantasy.api.availableprediction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailablePredictionRepository extends JpaRepository<AvailablePrediction, Long> {
}
