package harouane.u5w2d3RestAndDBII.DTOs;

import harouane.u5w2d3RestAndDBII.Entities.Author;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BlogpostDTO(
        @NotEmpty(message = "La categoria è obbligatoria")
        @Size(min = 3, message = "Il numero di caratteri deve essere compreso tra 3 e 25")
        String category,
        @NotEmpty(message = "Il titolo non può essere vuoto")
        @Size(min = 3, message = "Il numero di caratteri deve essere compreso tra 3 e 25")
        String title,
        @NotEmpty(message = "La copertina è obbligatoria")
        @Size(min = 3, message = "Il numero di caratteri deve essere compreso tra 3 e 25")
        String cover,
        @NotEmpty(message = "Il contenuto non può essere vuoto")
        @Size(min = 3, message = "Il numero di caratteri deve essere compreso tra 3 e 25")
        String content,
        @Min(value = 2, message = "Il tempo di lettura è obbligatorio e non può essere minore di due")
        int timeOfReading,
        @Min(value = 1, message = "L'autore è obbligatorio")
        int authorId) {

}
