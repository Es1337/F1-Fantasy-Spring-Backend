package zti.project.f1fantasy.api.raceresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
