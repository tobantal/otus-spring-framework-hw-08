package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Book;

public interface BookService {
	
	long size();
	
	Book findBookByName(String name);
	
	Book addBook(String name, String author, String genre);
	
	void deleteBookById(Long id);
	
	List<Book> findAllBooks();
	
	List<Book> findBooksByAuthor(String author);
	
	List<Book> findBooksByGenre(String genre);

}
