package zti.project.f1fantasy.api.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByFname(String fname);
    List<Driver> findByLname(String lname);
    List<Driver> findByFnameAndLname(String fname, String lname);
    List<Driver> findByFnameAndLnameAndSeasonId(String fname, String lname, Long seasonId);
    List<Driver> findByTeamId(Long teamId);
    List<Driver> findByTeamIdAndSeasonId(Long teamId, Long seasonId);
    List<Driver> findBySeasonId(Long seasonId);
}
