package zti.project.f1fantasy.api.season;

public class SeasonNotFoundException extends RuntimeException {
    public SeasonNotFoundException(Long seasonId) {
        super("No season with id: " + seasonId);
    }
}
