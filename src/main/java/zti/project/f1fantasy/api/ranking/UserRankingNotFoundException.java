package zti.project.f1fantasy.api.ranking;

public class UserRankingNotFoundException extends RuntimeException{
    public UserRankingNotFoundException(Long seasonId, Long userId){
        super("No user ranking with season ID: " + seasonId + " and user ID: " + userId);
    }
}
