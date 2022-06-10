package zti.project.f1fantasy.api.raceresult;

public class RaceResultNotFoundException extends RuntimeException{
    public RaceResultNotFoundException(Long raceResultId){
        super("No race result with ID: " + raceResultId);
    }
}
