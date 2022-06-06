package zti.project.f1fantasy.api.userprediction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPredictionRepository extends JpaRepository<UserPrediction, Long> {
}
