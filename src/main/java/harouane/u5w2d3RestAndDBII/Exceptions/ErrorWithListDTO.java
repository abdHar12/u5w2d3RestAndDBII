package harouane.u5w2d3RestAndDBII.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;

@Getter 
public class ErrorWithListDTO extends ErrorsPayload{

    List<String> errors;

    public ErrorWithListDTO(String message, LocalDateTime timestamp, List<String> errors) {
        super(message, timestamp);
        this.errors=errors;
    }
}
