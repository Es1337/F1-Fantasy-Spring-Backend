package zti.project.f1fantasy.api.driver;

public class DriverNotFoundException extends RuntimeException{
    public DriverNotFoundException(Long driverId){
        super("No driver with id: " + driverId);
    }
}
