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
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.BookRepositoryImpl;

@DataJpaTest
@Import(BookRepositoryImpl.class)
public class BookRepositoryImplTest {
	
	@Autowired
	BookRepository bookRerpository;
	
	@Autowired
	TestEntityManager em;

	@Test
	public void testInsert() {
		Author author = em.persist(new Author(null, "Zaitseva"));
		Genre genre = em.persist(new Genre(null, "algebra"));
		Book book = new Book(null, "Algebra for chidren", author, genre);
		long countBefore = bookRerpository.count();
		
		book = bookRerpository.save(book);
		
		assertEquals(countBefore + 1, bookRerpository.count());	
		Book savedBook = em.find(Book.class, book.getId());
		assertThat(savedBook).isEqualToComparingFieldByField(book);
	}
	
	@Test
	public void testGetById() {
		Author author = em.persist(new Author(null, "Romanov"));
		Genre genre = em.persist(new Genre(null, "geometry"));
		Book savedBook = em.persist(new Book(null, "Box&Circle", author, genre));
		
		Book book = bookRerpository.findById(savedBook.getId());
		
		assertNotNull(book);
		assertThat(savedBook).isEqualToComparingFieldByField(book);
	}
	
	@Test
	public void testGetAll() {
		long count = bookRerpository.count();
		
		List<Book> books = bookRerpository.findAll();
		
		assertEquals(count, books.size());
	}

	@Test
	public void testDeleteById() {
		Author author = em.persist(new Author(null, "Yulina"));
		Genre genre = em.persist(new Genre(null, "enlish"));
		Book savedBook = em.persist(new Book(null, "UK English", author, genre));
		long countBefore = bookRerpository.count();
		
		bookRerpository.deleteById(savedBook.getId());
		
		assertEquals(countBefore - 1, bookRerpository.count());
	}

	@Test
	public void testGetByName() {
		Author author = em.persist(new Author(null, "Tolstoy"));
		Genre genre = em.persist(new Genre(null, "Russian"));
		Book savedBook = em.persist(new Book(null, "War&Peace", author, genre));
		
		Book book = bookRerpository.findByName(savedBook.getName());
		
		assertNotNull(book);
		assertThat(savedBook).isEqualToComparingFieldByField(book);
	}

	@Test
	public void testGetAllByAuthor() {
		Author author = em.persist(new Author(null, "Kokorina"));
		Genre genre = em.persist(new Genre(null, "lirics"));
		Book savedBook = em.persist(new Book(null, "Red Sun", author, genre));
		
		List<Book> books = bookRerpository.findByAuthor(savedBook.getAuthor().getName());
		
		assertEquals(1, books.size());
	}

	@Test
	public void testGetAllByGenre() {
		Author author = em.persist(new Author(null, "Vilkin"));
		Genre genre = em.persist(new Genre(null, "pirates"));
		Book savedBook = em.persist(new Book(null, "Black Sails", author, genre));
		
		List<Book> books = bookRerpository.findByGenre(savedBook.getGenre().getName());
		
		assertEquals(1, books.size());
	}
}
