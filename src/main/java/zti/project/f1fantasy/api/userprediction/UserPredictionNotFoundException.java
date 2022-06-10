package zti.project.f1fantasy.api.userprediction;

public class UserPredictionNotFoundException extends RuntimeException {
    public UserPredictionNotFoundException(Long predictionId){
        super("No user prediction with ID: " + predictionId);
    }
}
