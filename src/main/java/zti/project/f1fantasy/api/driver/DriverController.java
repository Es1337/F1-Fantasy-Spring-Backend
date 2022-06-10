package zti.project.f1fantasy.api.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/driver")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getAllDrivers(){
        return driverService.getAllDrivers();
    }

    @GetMapping(path = "{driverId}")
    public Driver getDriverById(@PathVariable Long driverId){
        return driverService.getDriverById(driverId);
    }

    @GetMapping(path = "team/{teamId}")
    public List<Driver> getDriversFromTeam(@PathVariable Long teamId){
        return driverService.getDriversFromTeam(teamId);
    }

    @GetMapping(path = "season/{seasonId}")
    public List<Driver> getDriversFromSeason(@PathVariable Long seasonId){
        return driverService.getDriversFromSeason(seasonId);
    }

    @PostMapping(path = "{teamId}/{seasonId}")
    public Driver postDriver(@RequestBody Driver driver, @PathVariable Long teamId, @PathVariable Long seasonId){
        return driverService.addDriver(driver, teamId, seasonId);
    }

    @PutMapping(path = "{oldDriverId}")
    public Driver updateDriverById(@RequestBody Driver newDriver, @PathVariable Long oldDriverId) {
        return driverService.updateDriverById(newDriver, oldDriverId);
    }

    @DeleteMapping(path = "{driverId}")
    public void deleteDriverById(@PathVariable Long driverId) {
        driverService.deleteDriverById(driverId);
    }
}
