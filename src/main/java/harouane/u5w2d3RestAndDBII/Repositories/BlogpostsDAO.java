package harouane.u5w2d3RestAndDBII.Repositories;

import harouane.u5w2d3RestAndDBII.Entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogpostsDAO extends JpaRepository<Blogpost, Integer> {
    Optional<Blogpost> findById(int id);
    List<Blogpost> findAll();

    @Modifying
    @Query("delete from Blogpost b where b.author.id=:id")
    void removeByAuthorId(int id);
}
