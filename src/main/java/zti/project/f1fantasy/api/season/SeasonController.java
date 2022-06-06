package zti.project.f1fantasy.api.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/season")
public class SeasonController {
    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService){
        this.seasonService = seasonService;
    }

    @GetMapping
    public List<Season> getSeasons() {
        return seasonService.getSeasons();
    }

    @GetMapping(path = "{seasonId}")
    public Season getSeasonById(@PathVariable Long seasonId){
        return seasonService.getSeasonById(seasonId);
    }

    @GetMapping(path = "year/{year}")
    public List<Season> getSeasonByYear(@PathVariable Integer year){
        return seasonService.getSeasonByYear(year);
    }

    @PostMapping
    public Season postSeason(@RequestBody Season season) {
        return seasonService.addSeason(season);
    }

    @PutMapping(path = "{oldSeasonId}")
    public Season updateSeasonById(@RequestBody Season newSeason, @PathVariable Long oldSeasonId){
        return seasonService.updateSeasonById(newSeason, oldSeasonId);
    }

    @DeleteMapping(path = "{seasonId}")
    public void deleteSeasonById(@PathVariable Long seasonId) {
        seasonService.deleteSeasonById(seasonId);
    }
}
