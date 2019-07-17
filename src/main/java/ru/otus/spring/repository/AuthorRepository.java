package ru.otus.spring.repository;

import java.util.List;

import ru.otus.spring.domain.Author;

public interface AuthorRepository {
	
	long count();
	
	Author save(Author author);
	
	Author findById(Long id);
	
	List<Author> findAll();
	
	void deleteById(Long id);

	Author findByName(String name);
	
}
