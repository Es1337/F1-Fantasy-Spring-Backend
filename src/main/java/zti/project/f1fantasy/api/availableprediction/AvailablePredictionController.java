package zti.project.f1fantasy.api.availableprediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/available-prediction")
public class AvailablePredictionController {
    private final AvailablePredictionService availablePredictionService;

    @Autowired
    public AvailablePredictionController(AvailablePredictionService availablePredictionService) {
        this.availablePredictionService = availablePredictionService;
    }

    @GetMapping
    public List<AvailablePrediction> getAllAvailablePredictions(){
        return availablePredictionService.getAllAvailablePredictions();
    }

    @GetMapping(path = "{predictionId}")
    public AvailablePrediction getAvailablePredictionById(@PathVariable Long predictionId){
        return availablePredictionService.getPredictionById(predictionId);
    }

    @GetMapping(path = "season/{seasonId}")
    public List<AvailablePrediction> getAvailablePredictionsFromSeason(@PathVariable Long seasonId){
        return availablePredictionService.getPredictionsFromSeason(seasonId);
    }

    @GetMapping(path = "code/{typeCode}")
    public List<AvailablePrediction> getAvailablePredictionsWithCode(@PathVariable String typeCode){
        return availablePredictionService.getPredictionsWithCode(typeCode);
    }

    @PostMapping(path = "{seasonId}")
    public AvailablePrediction postAvailablePrediction(@RequestBody AvailablePrediction prediction, @PathVariable Long seasonId){
        return availablePredictionService.addAvailablePrediction(prediction, seasonId);
    }

    @PutMapping(path = "{oldPredictionId}")
    public AvailablePrediction updateAvailablePredictionById(@RequestBody AvailablePrediction newPrediction, @PathVariable Long oldPredictionId){
        return availablePredictionService.updatePredictionById(newPrediction, oldPredictionId);
    }

    @DeleteMapping(path = "{predictionId}")
    public void deleteAvailablePredictionById(@PathVariable Long predictionId){
        availablePredictionService.deletePredictionById(predictionId);
    }
}
