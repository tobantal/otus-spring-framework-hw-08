package ru.otus.spring.repository;

import java.util.List;

import ru.otus.spring.domain.Book;

public interface BookRepository {
	
	long count();
	
	Book save(Book book);
	
	Book findById(Long id);
	
	List<Book> findAll();
	
	void deleteById(Long id);

	Book findByName(String name);
	
	List<Book> findByAuthor(String author);
	
	List<Book> findByGenre(String genre);
	
}
