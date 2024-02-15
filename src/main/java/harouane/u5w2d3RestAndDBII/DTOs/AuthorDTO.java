package harouane.u5w2d3RestAndDBII.DTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthorDTO(
        @NotEmpty(message = "Il nome non può essere vuoto")
        @Size(min = 3, max = 25, message = "Il numero di caratteri deve essere compreso tra 3 e 25")
        String name,
        @NotEmpty(message = "Il cognome non può essere vuoto")
        @Size(min = 3, max = 25, message = "Il numero di caratteri deve essere compreso tra 3 e 25")
        String lastName,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "Non hai inserito un'email valida!")
        String email,
        @NotEmpty(message = "La data di nascita è obbligatoria")
        String dateOfBirth,
        String avatar)
{
}
