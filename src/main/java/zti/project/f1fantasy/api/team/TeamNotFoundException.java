package zti.project.f1fantasy.api.team;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(Long teamId) {
        super("No team with id: " + teamId);
    }
}
