package ru.otus.spring.repository;

import java.util.List;

import ru.otus.spring.domain.Genre;

public interface GenreRepository {
	
	long count();
	
	Genre save(Genre genre);
	
	Genre findById(Long id);
	
	List<Genre> findAll();
	
	void deleteById(Long id);

	Genre findByName(String name);
	
}
