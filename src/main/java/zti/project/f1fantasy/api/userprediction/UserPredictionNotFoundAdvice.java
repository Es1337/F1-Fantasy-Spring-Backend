package zti.project.f1fantasy.api.userprediction;

import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserPredictionNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(UserPredictionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userPredictionNotFoundHandler(UserPredictionNotFoundException ex){
        return ex.getMessage();
    }
}
