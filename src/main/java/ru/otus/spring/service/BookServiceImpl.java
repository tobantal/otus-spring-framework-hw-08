package ru.otus.spring.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final GenreService genreService;
	private final AuthorService authorService;

	@Override
	@Transactional(readOnly = true)
	public long size() {
		return bookRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public Book findBookByName(String name) {
		return bookRepository.findByName(name);
	}

	@Override
	public Book addBook(String name, String author, String genre) {		
		try {
			findBookByName(name);
			String error = String.format("The book with the name <%s> already exists", name);
			throw new PermissionDeniedDataAccessException(error, null);
		} catch(EmptyResultDataAccessException e) {
			Book book = new Book(null, name, authorService.createIfItIsNecessaryAndGet(author),
					genreService.createIfItIsNecessaryAndGet(genre));
			return bookRepository.save(book);
		}
	}

	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findBooksByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findBooksByGenre(String genre) {
		return bookRepository.findByGenre(genre);
	}

}
