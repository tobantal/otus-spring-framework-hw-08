package ru.otus.spring.dao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.repository.GenreRepositoryImpl;

@DataJpaTest
@Import(GenreRepositoryImpl.class)
public class GenreRepositoryImplTest {

	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	TestEntityManager em;
	
	@Test
	public void insert() {
		Genre genre = new Genre(null, "humor");
		long countBefore = genreRepository.count();
		
		genreRepository.save(genre);
		
		assertEquals(countBefore + 1, genreRepository.count());
		assertNotNull(genreRepository.findByName("humor"));
	}

	@Test
	public void getById() {
		Genre savedGenre = em.persist(new Genre(null, "trash"));
		
		Genre genre = genreRepository.findById(savedGenre.getId());
		
		assertNotNull(genre);
		assertThat(savedGenre).isSameAs(genre);
	}
	
	@Test
	public void getAll() {
		long count = genreRepository.count();
		
		List<Genre> genres = genreRepository.findAll();
		
		assertEquals(count, genres.size());
	}
	
	@Test
	public void deleteById() {
		Genre savedGenre = em.persist(new Genre(null, "diary"));		
		long countBefore = genreRepository.count();
		
		genreRepository.deleteById(savedGenre.getId());
		
		assertEquals(countBefore - 1, genreRepository.count());
	}
	
	@Test
	public void getByName() {
		Genre savedGenre = em.persist(new Genre(null, "western"));
		
		Genre genre = genreRepository.findByName(savedGenre.getName());
		
		assertNotNull(genre);
		assertThat(savedGenre).isSameAs(genre);
	}
}