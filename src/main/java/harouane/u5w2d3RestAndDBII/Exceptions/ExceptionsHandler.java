package harouane.u5w2d3RestAndDBII.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest(BadRequest ex){
        if(ex.getErrors()!=null) return new ErrorWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrors().stream().map(err-> err.getDefaultMessage()).toList());
        else {
            return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
        }
    }
    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFound ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleException(Exception ex){
        ex.printStackTrace();
        return new ErrorsPayload("Il problema verrà risolto", LocalDateTime.now());
    }

}
