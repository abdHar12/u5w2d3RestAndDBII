package harouane.u5w2d3RestAndDBII.Controllers;


import harouane.u5w2d3RestAndDBII.DTOs.AuthorDTO;
import harouane.u5w2d3RestAndDBII.Entities.Author;
import harouane.u5w2d3RestAndDBII.Exceptions.BadRequest;
import harouane.u5w2d3RestAndDBII.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy){
        return authorService.findAll(page, size, orderBy);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveNewAuthor(@RequestBody  @Validated AuthorDTO authorDTO, BindingResult validation){
        if(validation.hasErrors()) throw new BadRequest(validation.getAllErrors());
        else{
            return authorService.saveNewAuthor(authorDTO);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Author findById(@PathVariable int id){
        return authorService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author modifyAuthor(@PathVariable int id, @RequestBody @Validated AuthorDTO modifiedAuthor, BindingResult validation){
        if(validation.hasErrors()) throw new BadRequest(validation.getAllErrors());
        else{
            return authorService.findByIdAndUpdate(id, modifiedAuthor);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@PathVariable int id){
        authorService.findByIdAndDelete(id);
    }
}
