package zti.project.f1fantasy.api.userprediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
