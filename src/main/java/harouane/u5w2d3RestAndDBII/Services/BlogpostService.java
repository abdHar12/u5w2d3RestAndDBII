package harouane.u5w2d3RestAndDBII.Services;

import harouane.u5w2d3RestAndDBII.DTOs.BlogpostDTO;
import harouane.u5w2d3RestAndDBII.Entities.Blogpost;
import harouane.u5w2d3RestAndDBII.Exceptions.NotFound;
import harouane.u5w2d3RestAndDBII.Repositories.BlogpostsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service

public class BlogpostService {
    @Autowired
    BlogpostsDAO blogpostsDAO;
    @Autowired
    AuthorService authorService;
    public Page<Blogpost> findAll(int pageNumber, int size, String orderBy){
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogpostsDAO.findAll(pageable);
    }
    public Blogpost saveNewBlogpost(BlogpostDTO blogpostPayload){
        Blogpost blogpost=new Blogpost(blogpostPayload.category(), blogpostPayload.title(), blogpostPayload.cover(), blogpostPayload.content(), blogpostPayload.timeOfReading(), authorService.findById(blogpostPayload.authorId()));
        blogpostsDAO.save(blogpost);
        return blogpost;
    }

    public Blogpost findById(int id){
        return blogpostsDAO.findById(id).orElseThrow(()->new NotFound("L'id "+ id+ " non è presente"));
    }

    public Blogpost findByIdAndUpdate(int id, BlogpostDTO blogpostPayload) {
        Blogpost found = findById(id);
        found.setTitle(blogpostPayload.title());
        found.setCategory(blogpostPayload.category());
        found.setCover(blogpostPayload.cover());
        found.setContent(blogpostPayload.content());
        found.setTimeOfReading(blogpostPayload.timeOfReading());
        found.setAuthor(authorService.findById(blogpostPayload.authorId()));
        return blogpostsDAO.save(found);
    }

    public void findByIdAndDelete(int id){
        Blogpost found = findById(id);
        blogpostsDAO.delete(found);
    }
}
