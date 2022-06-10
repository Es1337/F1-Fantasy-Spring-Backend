package zti.project.f1fantasy.api.race;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    public List<Race> findByName(String name);
    public List<Race> findByCity(String city);
    public List<Race> findByCountry(String country);
    public List<Race> findByTrack(String track);
    public List<Race> findBySeasonId(Long seasonId);
}
