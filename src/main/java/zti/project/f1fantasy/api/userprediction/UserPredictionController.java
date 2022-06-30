package zti.project.f1fantasy.api.userprediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user-prediction")
public class UserPredictionController {
    private final UserPredictionsService userPredictionsService;

    @Autowired
    public UserPredictionController(UserPredictionsService userPredictionsService) {
        this.userPredictionsService = userPredictionsService;
    }

    @GetMapping
    public List<UserPrediction> getAllUserPredictions(){
        return userPredictionsService.getAllUserPredictions();
    }

    @GetMapping(path = "{predictionId}")
    public UserPrediction getUserPredictionById(@PathVariable Long predictionId){
        return userPredictionsService.getPredictionById(predictionId);
    }

    @GetMapping(path = "{raceId}/{userid}")
    public List<UserPrediction> getUserPredictionsFromRaceByUserId(@PathVariable Long raceId, @PathVariable Long userId){
        return userPredictionsService.getPredictionsFromRaceFromUser(raceId, userId);
    }

    @GetMapping(path = "{raceId}/{userId}/{avPredictionId}")
    public List<UserPrediction> getPredictionFromRaceByUserIdWithAvPredictionId(@PathVariable Long raceId,
                                                                                @PathVariable Long userId,
                                                                                @PathVariable Long avPredictionId){
        return userPredictionsService.getPredictionsWithIdFromRaceFromUser(avPredictionId, raceId, userId);
    }

    @PostMapping(path = "{raceId}/{userId}/{avPredictionId}")
    public UserPrediction postPrediction(@RequestBody UserPrediction prediction,
                                         @PathVariable Long raceId,
                                         @PathVariable Long userId,
                                         @PathVariable Long avPredictionId) {
        return userPredictionsService.addUserPrediction(prediction, avPredictionId, raceId, userId);
    }

    @PutMapping(path = "{oldPredictionId}")
    public UserPrediction updatePredictionById(@RequestBody UserPrediction newPrediction,
                                               @PathVariable Long oldPredictionId){
        return userPredictionsService.updateUserPredictionById(newPrediction, oldPredictionId);
    }

    @DeleteMapping(path = "{predictionId}")
    public void deletePredictionById(@PathVariable Long predictionId){
        userPredictionsService.deleteUserPredictionById(predictionId);
    }
}
