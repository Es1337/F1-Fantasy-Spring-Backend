package zti.project.f1fantasy.api.raceresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/race-result")
public class RaceResultController {
    private final RaceResultService raceResultService;

    @Autowired
    public RaceResultController(RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }

    @GetMapping
    public List<RaceResult> getAllRaceResults(){
        return raceResultService.getAllRaceResults();
    }

    @GetMapping(path = "{resultId}")
    public RaceResult getResultById(@PathVariable Long resultId){
        return raceResultService.getResultById(resultId);
    }

    @GetMapping(path = "driver/{driverId}")
    public List<RaceResult> getResultsFromDriver(@PathVariable Long driverId){
        return raceResultService.getResultsFromDriver(driverId);
    }

    @GetMapping(path = "race/{raceId}")
    public List<RaceResult> getResultsFromRace(@PathVariable Long raceId){
        return raceResultService.getResultsFromRace(raceId);
    }

    @PostMapping(path = "{driverId}/{raceId}")
    public RaceResult postResult(@RequestBody RaceResult result, @PathVariable Long driverId, @PathVariable Long raceId){
        return raceResultService.addResult(result, driverId, raceId);
    }

    @PutMapping(path = "{oldResultId}")
    public RaceResult updateResultById(@RequestBody RaceResult result, @PathVariable Long oldResultId){
        return raceResultService.updateResultById(result, oldResultId);
    }

    @DeleteMapping(path = "{resultId}")
    public void deleteResultById(@PathVariable Long resultId){
        raceResultService.deleteResultById(resultId);
    }
}
