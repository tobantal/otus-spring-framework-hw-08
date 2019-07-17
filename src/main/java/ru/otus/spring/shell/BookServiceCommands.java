package ru.otus.spring.shell;

import java.sql.SQLException;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.ConsoleServiceImpl;
import ru.otus.spring.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class BookServiceCommands {
	
	private final ConsoleServiceImpl consoleService;
	private final BookService bookService;
	private final AuthorService authorService;
	private final GenreService genreService;
	private final CommentService commentService;
	
	@ShellMethod("count books")
	public void countBooks() {
		consoleService.write("books are %d", bookService.size());
	}
	
	@ShellMethod("add new book")
	public void addBook(String name, String author, String genre) {
		Book book = bookService.addBook(name, author, genre);
		consoleService.write("book has been saved:\n\t%s", book);
	}
	
	@ShellMethod("find book by name")
	public void findBook(String name) {
		consoleService.write("%s", bookService.findBookByName(name));
	}
	
	@ShellMethod("find book by author")
	public void findBooksByAuthor(String author) {
		bookService.findBooksByAuthor(author).forEach(b -> consoleService.write("%s",  b.toString()));	
	}
	
	@ShellMethod("find book by genre")
	public void findBooksByGenre(String genre) {
		bookService.findBooksByGenre(genre).forEach(b -> consoleService.write("%s",  b.toString()));	
	}
	
	@ShellMethod("find all books")
	public void findAllBooks() {
		bookService.findAllBooks().forEach(b -> consoleService.write("%s", b.toString()));		
	}
	
	@ShellMethod("find all authors")
	public void findAllAuthors() {
		authorService.findAll().forEach(a -> consoleService.write("%s", a.toString()));		
	}
	
	@ShellMethod("find all genres")
	public void findAllGenres() {
		genreService.findAll().forEach(g -> consoleService.write("%s", g.toString()));		
	}
	
	@ShellMethod("delete book by id")
	public void deleteBook(Long id) {
		bookService.deleteBookById(id);
		consoleService.write("book with id=%s has been deleted", id);
	}
	
	@ShellMethod("delete author by id")
	public void deleteAuthor(Long id) {
		authorService.deleteById(id);
		consoleService.write("author with id=%s has been deleted", id);
	}
	
	@ShellMethod("delete genre by id")
	public void deleteGenre(Long id) {
		genreService.deleteById(id);
		consoleService.write("genre with id=%s has been deleted", id);
	}
	
	@ShellMethod("add comment to a book")
	public void comment(String name, String comment) throws SQLException {
		Book book = bookService.findBookByName(name);
		commentService.addComment(new Comment(comment, book));
	}
	
	@ShellMethod("show all comments for a book")
	public void comments(String name) throws SQLException {
		Book book = bookService.findBookByName(name);
		commentService.getCommentsForBook(book).forEach(c -> consoleService.write("%s", c));	
	}
}
