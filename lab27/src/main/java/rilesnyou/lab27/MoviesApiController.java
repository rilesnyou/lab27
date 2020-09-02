package rilesnyou.lab27;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import rilesnyou.lab27.dao.MoviesDao;
import rilesnyou.lab27.entity.Movies;

@RestController
public class MoviesApiController {
	
	@Autowired
	private MoviesDao dao;
	
	
	@GetMapping("/movie")
	public List<Movies> listMovies(@RequestParam(required=false)String name) {
		if(name == null || name.isEmpty()) {
			return dao.findAll();
		} else {
			return dao.findByNameContainsIgnoreCase(name);
		}
	}
	@GetMapping("/movie/{id}")
	public Movies oneMovie(@PathVariable("id") Long id) {
		return dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such movie."));
	}
	@GetMapping("/movie/category")
	public List<Movies> catMovies(@RequestParam(required=false) String category) {
		return dao.findByNameContainsIgnoreCase(category);
	}
	@GetMapping("/categories")
	public List<Movies> movieCategories(Sort categories) {
		return dao.findAll(categories);
	}
	@GetMapping("/random-movie")
	public Optional<Movies> randomMovie(@RequestBody Movies ranMovie, Long x) {
		x = (long) Math.random();
		return dao.findById(x);
	}
	@DeleteMapping("/movie/{id}")
	public void deleteMovie(@PathVariable("id") Long id) {
		dao.deleteById(id);
	}
	@PostMapping("/movie")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Movies createProduct(@RequestBody Movies newMovie) {
		return dao.save(newMovie);
	}
}
