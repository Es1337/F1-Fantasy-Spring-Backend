package zti.project.f1fantasy.api.availableprediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.season.SeasonService;

import java.util.List;

@Service
public class AvailablePredictionService {
    private final AvailablePredictionRepository availablePredictionRepository;
    private final SeasonService seasonService;

    @Autowired
    public AvailablePredictionService(AvailablePredictionRepository availablePredictionRepository, SeasonService seasonService) {
        this.availablePredictionRepository = availablePredictionRepository;
        this.seasonService = seasonService;
    }

    public List<AvailablePrediction> getAllAvailablePredictions(){
        return availablePredictionRepository.findAll();
    }

    public AvailablePrediction getPredictionById(Long predictionId){
        return availablePredictionRepository.findById(predictionId).orElseThrow(() -> new AvailablePredictionNotFoundException(predictionId));
    }

    public List<AvailablePrediction> getPredictionsFromSeason(Long seasonId){
        return availablePredictionRepository.findBySeasonId(seasonId);
    }

    public List<AvailablePrediction> getPredictionsWithCode(String typeCode){
        return availablePredictionRepository.findByTypeCode(typeCode);
    }

    public AvailablePrediction addAvailablePrediction(AvailablePrediction prediction, Long seasonId){
        Season season = seasonService.getSeasonById(seasonId);
        prediction.setSeason(season);

        return availablePredictionRepository.save(prediction);
    }

    public AvailablePrediction updatePredictionById(AvailablePrediction newPrediction, Long oldPredictionId){
        return availablePredictionRepository.findById(oldPredictionId).map(prediction -> {
            prediction.setTypeCode(newPrediction.getTypeCode());
            prediction.setTarget(newPrediction.getTarget());
            return availablePredictionRepository.save(prediction);
        }).orElseGet(() -> {
            newPrediction.setId(oldPredictionId);
            return availablePredictionRepository.save(newPrediction);
        });
    }

    public void deletePredictionById(Long predictionId){
        AvailablePrediction predictionToDelete = availablePredictionRepository.findById(predictionId).get();
        availablePredictionRepository.delete(predictionToDelete);
    }
}
