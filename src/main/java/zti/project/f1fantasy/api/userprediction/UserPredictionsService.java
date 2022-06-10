package zti.project.f1fantasy.api.userprediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.project.f1fantasy.api.availableprediction.AvailablePrediction;
import zti.project.f1fantasy.api.availableprediction.AvailablePredictionService;
import zti.project.f1fantasy.api.race.Race;
import zti.project.f1fantasy.api.race.RaceService;
import zti.project.f1fantasy.api.user.User;
import zti.project.f1fantasy.api.user.UserService;

import java.util.List;

@Service
public class UserPredictionsService {
    private final UserPredictionRepository userPredictionRepository;
    private final AvailablePredictionService availablePredictionService;
    private final RaceService raceService;
    private final UserService userService;

    @Autowired
    public UserPredictionsService(UserPredictionRepository userPredictionRepository,
                                  AvailablePredictionService availablePredictionService,
                                  RaceService raceService,
                                  UserService userService) {
        this.userPredictionRepository = userPredictionRepository;
        this.availablePredictionService = availablePredictionService;
        this.raceService = raceService;
        this.userService = userService;
    }

    public List<UserPrediction> getAllUserPredictions(){
        return userPredictionRepository.findAll();
    }

    public UserPrediction getPredictionById(Long predictionId){
        return userPredictionRepository.findById(predictionId)
                .orElseThrow(() -> new UserPredictionNotFoundException(predictionId));
    }

    public List<UserPrediction> getPredictionsFromRaceFromUser(Long raceId, Long userId){
        return userPredictionRepository.findByRaceIdAndUserId(raceId, userId);
    }

    public List<UserPrediction> getPredictionsWithIdFromRaceFromUser(Long availablePredictionId,
                                                                     Long raceId,
                                                                     Long userId){
        return userPredictionRepository.findByAvailablePredictionIdAndRaceIdAndUserId(availablePredictionId, raceId, userId);
    }

    public UserPrediction addUserPrediction(UserPrediction prediction,
                                            Long availablePredictionId,
                                            Long raceId,
                                            Long userId){
        AvailablePrediction availablePrediction = availablePredictionService.getPredictionById(availablePredictionId);
        Race race = raceService.getRaceById(raceId);
        User user = userService.getUserById(userId);

        prediction.setAvailablePrediction(availablePrediction);
        prediction.setRace(race);
        prediction.setUser(user);

        return userPredictionRepository.save(prediction);
    }

    public UserPrediction updateUserPredictionById(UserPrediction newPrediction, Long oldPredictionId){
        return userPredictionRepository.findById(oldPredictionId).map(prediction -> {
            prediction.setTitle(newPrediction.getTitle());

            return userPredictionRepository.save(prediction);
        }).orElseGet(() -> {
            newPrediction.setId(oldPredictionId);

            return userPredictionRepository.save(newPrediction);
        });
    }

    public void deleteUserPredictionById(Long predictionId){
        UserPrediction predictionToDelete = userPredictionRepository.findById(predictionId).get();
        userPredictionRepository.delete(predictionToDelete);
    }
}
