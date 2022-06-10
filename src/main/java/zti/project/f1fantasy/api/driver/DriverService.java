package zti.project.f1fantasy.api.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.season.SeasonService;
import zti.project.f1fantasy.api.team.Team;
import zti.project.f1fantasy.api.team.TeamService;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;
    private final SeasonService seasonService;
    private final TeamService teamService;

    @Autowired
    public DriverService(DriverRepository driverRepository, SeasonService seasonService, TeamService teamService) {
        this.driverRepository = driverRepository;
        this.seasonService = seasonService;
        this.teamService = teamService;
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException(driverId));
    }

    public List<Driver> getDriversFromTeam(Long teamId) {
        return driverRepository.findByTeamId(teamId);
    }

    public List<Driver> getDriversFromSeason(Long seasonId) {
        return driverRepository.findBySeasonId(seasonId);
    }

    public List<Driver> getDriversByFname(String fname) {
        return driverRepository.findByFname(fname);
    }

    public List<Driver> getDriversByLname(String lname) {
        return driverRepository.findByLname(lname);
    }

    public List<Driver> getDriversByFullName(String fname, String lname) {
        return driverRepository.findByFnameAndLname(fname, lname);
    }

    public Driver addDriver(Driver driver, Long teamId, Long seasonId) {
        Season season = seasonService.getSeasonById(seasonId);
        Team team = teamService.getTeamById(teamId);

        driver.setSeason(season);
        driver.setTeam(team);

        return driverRepository.save(driver);
    }

    public Driver updateDriverById(Driver newDriver, Long oldDriverId){
        return driverRepository.findById(oldDriverId).map(driver -> {
            driver.setFname(newDriver.getFname());
            driver.setLname(newDriver.getLname());
            driver.setPoints(newDriver.getPoints());

            return driverRepository.save(driver);
        }).orElseGet(() -> {
            newDriver.setId(oldDriverId);
            return driverRepository.save(newDriver);
        });
    }

    public void deleteDriverById(Long driverId){
        Driver driverToDelete = driverRepository.findById(driverId).get();
        driverRepository.delete(driverToDelete)

        ;
    }
}
