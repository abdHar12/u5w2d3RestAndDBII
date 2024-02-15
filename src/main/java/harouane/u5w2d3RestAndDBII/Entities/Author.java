package harouane.u5w2d3RestAndDBII.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    int id;
    String name;
    String lastName;
    String email;
    @Column(name="date_of_birth")
    String dateOfBirth;
    String avatar;

    public Author(String name, String lastName, String email, String dateOfBirth) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Blogpost> blogposts;
}
