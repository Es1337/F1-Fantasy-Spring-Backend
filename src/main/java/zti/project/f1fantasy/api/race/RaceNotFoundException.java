package zti.project.f1fantasy.api.race;

public class RaceNotFoundException extends RuntimeException{
    public RaceNotFoundException(Long raceId) {
        super("No race with ID: " + raceId);
    }
}
