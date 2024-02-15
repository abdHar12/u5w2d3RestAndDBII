package harouane.u5w2d3RestAndDBII.Entities;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "blogposts")
public class Blogpost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    @Column(name = "time_of_reading")
    private int timeOfReading;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Blogpost(String category, String title, String cover, String content, int timeOfReading, Author author) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.timeOfReading = timeOfReading;
        this.author = author;
    }
}
