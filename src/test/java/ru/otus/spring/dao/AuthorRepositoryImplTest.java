package ru.otus.spring.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.AuthorRepositoryImpl;

@DataJpaTest
@Import(AuthorRepositoryImpl.class)
public class AuthorRepositoryImplTest {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	TestEntityManager em;

	@Test
	public void insert() {
		Author author = new Author(null, "Pushkin");
		long countBefore = authorRepository.count();
		
		author = authorRepository.save(author);
		
		assertEquals(countBefore + 1, authorRepository.count());
		assertNotNull(em.find(Author.class, author.getId()));
	}

	@Test
	public void getById() {
		Author savedAuthor = em.persist(new Author(null, "Tutkin"));
		
		Author foundAuthor = authorRepository.findById(savedAuthor.getId());
		
		assertNotNull(foundAuthor);
		assertThat(savedAuthor).isSameAs(foundAuthor);
	}
	
	@Test
	public void getAll() {
		long count = authorRepository.count();
		
		List<Author> authors = authorRepository.findAll();
		
		assertEquals(count, authors.size());
	}
	
	@Test
	public void deleteById() {
		Author savedAuthor = em.persistAndFlush(new Author(null, "Tutkinova"));
		long countBefore = authorRepository.count();
		
		authorRepository.deleteById(savedAuthor.getId());
		
		assertEquals(countBefore - 1, authorRepository.count());		
	}
	
	@Test
	public void getByName() {
		Author savedAuthor = em.persist(new Author(null, "Pupkin"));
		
		Author foundAuthor = authorRepository.findByName(savedAuthor.getName());
		
		assertNotNull(foundAuthor);
		assertThat(savedAuthor).isSameAs(foundAuthor);
	}
	
}
