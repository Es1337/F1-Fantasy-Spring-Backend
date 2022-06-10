package zti.project.f1fantasy.api.availableprediction;

public class AvailablePredictionNotFoundException extends RuntimeException{
    public AvailablePredictionNotFoundException(Long avPredicitonId){
        super("No prediction available with ID: " + avPredicitonId);
    }
}
