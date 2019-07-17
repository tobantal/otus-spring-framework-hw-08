package ru.otus.spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.AuthorRepositoryImpl;

@DataJpaTest
@Import(AuthorRepositoryImpl.class)
public class AuthorRepositoryImplTest {
	
	@Autowired
	AuthorRepository authorRepository;

	@Test
	public void insert() {
		Author author = new Author(null, "Pushkin");
		long countBefore = authorRepository.count();
		authorRepository.save(author);
		assertEquals(countBefore + 1, authorRepository.count());
		assertNotNull(authorRepository.findByName("Pushkin"));
	}

	@Test
	public void getById() {
		Author author = authorRepository.findById(2L);
		assertNotNull(author);
		assertEquals("Petrov", author.getName());
	}
	
	@Test
	public void getAll() {
		List<Author> authors = authorRepository.findAll();
		long count = authorRepository.count();
		assertEquals(count, authors.size());
	}
	
	@Test
	public void deleteById() {
		long countBefore = authorRepository.count();
		authorRepository.deleteById(4L);
		assertEquals(countBefore - 1, authorRepository.count());
		assertNull(authorRepository.findById(4L));
	}
	
	@Test
	public void getByName() {
		String name = "Ivanov";
		Author author = authorRepository.findByName(name);
		assertNotNull(author);
		assertEquals("Ivanov", author.getName());
	}
	
}
