package zti.project.f1fantasy.api.availableprediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
