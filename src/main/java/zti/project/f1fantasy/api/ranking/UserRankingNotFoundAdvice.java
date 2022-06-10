package zti.project.f1fantasy.api.ranking;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserRankingNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(UserRankingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userRankingNotFoundHandler(UserRankingNotFoundException ex){
        return ex.getMessage();
    }
}
