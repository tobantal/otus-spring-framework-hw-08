package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Author;

public interface AuthorService {
	
	Author createIfItIsNecessaryAndGet(String author);
	
	long size();
	
	void deleteById(Long id);
	
	List<Author> findAll();
	
}
