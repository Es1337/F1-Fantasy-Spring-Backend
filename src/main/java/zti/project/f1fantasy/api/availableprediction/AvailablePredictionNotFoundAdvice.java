package zti.project.f1fantasy.api.availableprediction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AvailablePredictionNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AvailablePredictionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String availablePredictionNotFoundHandler(AvailablePredictionNotFoundException ex){
        return ex.getMessage();
    }
}
