package rilesnyou.lab27.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rilesnyou.lab27.entity.Movies;

public interface MoviesDao extends JpaRepository<Movies, Long> {
	
	List<Movies> findByNameContainsIgnoreCase(String nameMatch);
}
