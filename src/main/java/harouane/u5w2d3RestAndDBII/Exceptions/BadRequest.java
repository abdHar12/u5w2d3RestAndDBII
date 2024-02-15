package harouane.u5w2d3RestAndDBII.Exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequest extends RuntimeException{

    List<ObjectError> errors;
    public BadRequest(String message){
        super(message);
    }

    public BadRequest(List<ObjectError> errors){
        super("Gli errori sono: ");
        this.errors=errors;
    }
}
