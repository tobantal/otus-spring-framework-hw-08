package ru.otus.spring.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.repository.GenreRepositoryImpl;

@DataJpaTest
@Import(GenreRepositoryImpl.class)
public class GenreRepositoryImplTest {

	@Autowired
	GenreRepository genreRepository;
	
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
		Genre genre = genreRepository.findById(2L);
		assertNotNull(genre);
		assertEquals("horrors", genre.getName());
	}
	
	@Test
	public void getAll() {
		List<Genre> genres = genreRepository.findAll();
		long count = genreRepository.count();
		assertEquals(count, genres.size());
	}
	
	@Test
	public void deleteById() {
		long countBefore = genreRepository.count();
		genreRepository.deleteById(4L);
		assertEquals(countBefore - 1, genreRepository.count());
		assertNull(genreRepository.findById(4L));
	}
	
	@Test
	public void getByName() {
		String name = "horrors";
		Genre genre = genreRepository.findByName(name);
		assertNotNull(genre);
		assertEquals("horrors", genre.getName());
	}
}