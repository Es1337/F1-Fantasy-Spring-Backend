package zti.project.f1fantasy.api.userprediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPredictionsService {
    private final UserPredictionRepository userPredictionRepository;

    @Autowired
    public UserPredictionsService(UserPredictionRepository userPredictionRepository) {
        this.userPredictionRepository = userPredictionRepository;
    }

    public List<UserPrediction> getAllUserPredictions(){
        return userPredictionRepository.findAll();
    }
}
