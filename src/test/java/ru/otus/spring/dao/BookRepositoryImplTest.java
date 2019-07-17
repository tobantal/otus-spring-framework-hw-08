package ru.otus.spring.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

	@Test
	public void testInsert() {
		Author author = new Author(1L, "Ivanov");
		Genre genre = new Genre(1L, "comics");
		Book book = new Book(null, "YoYo", author, genre);
		long countBefore = bookRerpository.count();
		bookRerpository.save(book);
		assertEquals(countBefore + 1, bookRerpository.count());
		Book savedBook = bookRerpository.findByName("YoYo");
		assertThat(savedBook.getAuthor()).isEqualToComparingFieldByField(author);
		assertThat(savedBook.getGenre()).isEqualToComparingFieldByField(genre);
	}
	
	@Test
	public void testGetById() {
		Book book = bookRerpository.findById(1L);
		assertNotNull(book);
		assertEquals("Desert rose", book.getName());
	}
	
	@Test
	public void testGetAll() {
		List<Book> books = bookRerpository.findAll();
		long count = bookRerpository.count();
		assertEquals(count, books.size());
	}

	@Test
	public void testDeleteById() {
		long countBefore = bookRerpository.count();
		bookRerpository.deleteById(3L);
		assertEquals(countBefore - 1, bookRerpository.count());
		assertNull(bookRerpository.findById(3L));		
	}

	@Test
	public void testGetByName() {
		String name = "Desert rose";
		Book book = bookRerpository.findByName(name);
		assertNotNull(book);
		assertThat(book.getId()).isEqualTo(1L);
	}

	@Test
	public void testGetAllByAuthor() {
		String name = "Petrov";
		List<Book> books = bookRerpository.findByAuthor(name);
		assertEquals(1, books.size());
	}

	@Test
	public void testGetAllByGenre() {
		String name = "fantasy";
		List<Book> books = bookRerpository.findByGenre(name);
		assertEquals(1, books.size());
	}
}
