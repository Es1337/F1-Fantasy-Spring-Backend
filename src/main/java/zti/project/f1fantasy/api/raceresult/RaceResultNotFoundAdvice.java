package zti.project.f1fantasy.api.raceresult;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RaceResultNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(RaceResultNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String raceResultNotFoundHandler(RaceResultNotFoundException ex) {
        return ex.getMessage();
    }
}
