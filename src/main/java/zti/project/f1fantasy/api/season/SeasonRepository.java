package zti.project.f1fantasy.api.season;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findByYear(Integer year);
    List<Season> findByWdc(String wdc);
    List<Season> findByWcc(String wcc);
}
