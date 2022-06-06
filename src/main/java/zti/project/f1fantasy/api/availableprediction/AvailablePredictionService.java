package zti.project.f1fantasy.api.availableprediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailablePredictionService {
    private final AvailablePredictionRepository availablePredictionRepository;

    @Autowired
    public AvailablePredictionService(AvailablePredictionRepository availablePredictionRepository) {
        this.availablePredictionRepository = availablePredictionRepository;
    }

    public List<AvailablePrediction> getAllAvailablePredictions(){
        return availablePredictionRepository.findAll();
    }
}
