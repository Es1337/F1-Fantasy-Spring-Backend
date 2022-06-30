package zti.project.f1fantasy.api.raceresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.project.f1fantasy.api.driver.Driver;
import zti.project.f1fantasy.api.driver.DriverService;
import zti.project.f1fantasy.api.race.Race;
import zti.project.f1fantasy.api.race.RaceService;

import java.util.List;

@Service
public class RaceResultService {
    private final RaceResultRepository raceResultRepository;
    private final DriverService driverService;
    private final RaceService raceService;

    @Autowired
    public RaceResultService(RaceResultRepository raceResultRepository, DriverService driverService, RaceService raceService) {
        this.raceResultRepository = raceResultRepository;
        this.driverService = driverService;
        this.raceService = raceService;
    }

    public List<RaceResult> getAllRaceResults(){
        return raceResultRepository.findAll();
    }

    public RaceResult getResultById(Long resultId){
        return raceResultRepository.findById(resultId).orElseThrow(() -> new RaceResultNotFoundException(resultId));
    }

    public List<RaceResult> getResultsFromDriver(Long driverId){
        return raceResultRepository.findByDriverId(driverId);
    }

    public List<RaceResult> getResultsFromRace(Long raceId){
        return raceResultRepository.findByRaceId(raceId);
    }

    public List<RaceResult> getResultsFromRaceForDriver(Long raceId, Long driverId){
        return raceResultRepository.findByRaceIdAndDriverId(raceId, driverId);
    }

    public RaceResult addResult(RaceResult result, Long driverId, Long raceId){
        Driver driver = driverService.getDriverById(driverId);
        Race race = raceService.getRaceById(raceId);

        result.setDriver(driver);
        result.setRace(race);

        return raceResultRepository.save(result);
    }

    public RaceResult updateResultById(RaceResult newResult, Long oldResultId){
        return raceResultRepository.findById(oldResultId).map(result -> {
            result.setPosition(newResult.getPosition());
            result.setPoints(newResult.getPoints());
            result.setDnf(newResult.isDnf());
            result.setFastestLap(newResult.getFastestLap());

            return raceResultRepository.save(result);
        }).orElseGet(() -> {
            newResult.setId(oldResultId);
            return raceResultRepository.save(newResult);
        });
    }

    public void deleteResultById(Long resultId){
        RaceResult resultToDelete = raceResultRepository.findById(resultId).orElseThrow(() -> new RaceResultNotFoundException(resultId));
        raceResultRepository.delete(resultToDelete);
    }
}

