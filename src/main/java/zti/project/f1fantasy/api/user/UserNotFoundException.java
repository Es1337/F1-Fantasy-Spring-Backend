package zti.project.f1fantasy.api.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId){
        super("No user with ID: " + userId);
    }

    public UserNotFoundException(String email){
        super("No user with email: " + email);
    }
}
