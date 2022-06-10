package zti.project.f1fantasy.api.race;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/race")
public class RaceController {
    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> getAllRaces(){
        return raceService.getAllRaces();
    }

    @GetMapping(path = "{raceId}")
    public Race getRaceById(@PathVariable Long raceId){
        return raceService.getRaceById(raceId);
    }

    @GetMapping(path = "name/{name}")
    public List<Race> getRacesByName(@PathVariable String name){
        return raceService.getRacesByName(name);
    }

    @GetMapping(path = "city/{city}")
    public List<Race> getRacesByCity(@PathVariable String city){
        return raceService.getRacesByCity(city);
    }

    @GetMapping(path = "country/{country}")
    public List<Race> getRacesByCountry(@PathVariable String country){
        return raceService.getRacesByCountry(country);
    }

    @GetMapping(path = "track/{track}")
    public List<Race> getRacesByTrack(@PathVariable String track){
        return raceService.getRacesByTrack(track);
    }

    @GetMapping(path = "season/{seasonId}")
    public List<Race> getRacesBySeason(@PathVariable Long seasonId){
        return raceService.getRacesFromSeason(seasonId);
    }

    @PostMapping(path = "{seasonId}")
    public Race postRace(@RequestBody Race race, @PathVariable Long seasonId){
        return raceService.addRace(race, seasonId);
    }

    @PutMapping(path = "{oldRaceId}")
    public Race updateRaceById(@RequestBody Race race, @PathVariable Long oldRaceId){
        return raceService.updateRaceById(race, oldRaceId);
    }

    @DeleteMapping(path = "{raceId}")
    public void deleteRaceById(@PathVariable Long raceId){
        raceService.deleteRaceById(raceId);
    }
}
