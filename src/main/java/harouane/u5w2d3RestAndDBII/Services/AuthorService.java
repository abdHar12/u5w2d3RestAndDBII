package harouane.u5w2d3RestAndDBII.Services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import harouane.u5w2d3RestAndDBII.DTOs.AuthorDTO;
import harouane.u5w2d3RestAndDBII.Entities.Author;
import harouane.u5w2d3RestAndDBII.Exceptions.BadRequest;
import harouane.u5w2d3RestAndDBII.Exceptions.NotFound;
import harouane.u5w2d3RestAndDBII.Repositories.AuthorsDAO;
import harouane.u5w2d3RestAndDBII.Repositories.BlogpostsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorsDAO authorsDAO;
    @Autowired
    BlogpostsDAO blogpostsDAO;
    @Autowired
    Cloudinary cloudinaryUploader;
    public Page<Author> findAll(int pageNumber, int size, String orderBy){
        if (size > 100) size = 100;
        Pageable pageable=PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return authorsDAO.findAll(pageable);
    }
     public Author saveNewAuthor(AuthorDTO authorDTO){
         if(!authorsDAO.findByEmail(authorDTO.email()).isEmpty()) throw new BadRequest("L'email inserità è gia presente");
         Author author= new Author(authorDTO.name(), authorDTO.lastName(), authorDTO.email(), authorDTO.dateOfBirth());
         author.setAvatar("https://ui-avatars.com/api/?name"+author.getName()+"+"+author.getLastName());
         authorsDAO.save(author);
         return author;
     }
    public Author findById(int id) {
         Optional<Author> found= authorsDAO.findById(id);
         return found.orElseThrow(() -> new NotFound("L'id " + id + " non è presente"));
    }
    public Author findByIdAndUpdate(int id, AuthorDTO modifiedAuthor){
        Author found = findById(id);
        found.setAvatar(modifiedAuthor.avatar());
        found.setName(modifiedAuthor.name());
        found.setLastName(modifiedAuthor.lastName());
        found.setEmail(modifiedAuthor.email());
        found.setDateOfBirth(modifiedAuthor.dateOfBirth() );
        return authorsDAO.save(found);
    }
    public void findByIdAndDelete(int id){
        Author found = findById(id);
        authorsDAO.delete(found);
    }


    public Author uploadImage(int id, MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        Author found= findById(id);
        found.setAvatar(url);
        return authorsDAO.save(found);
    }
}
