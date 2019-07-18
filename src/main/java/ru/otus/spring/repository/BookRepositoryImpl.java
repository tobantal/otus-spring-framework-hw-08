package ru.otus.spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long count() {
		return (Long) em.createQuery("select count(b) from Book b").getSingleResult();
	}

	@Override
	public Book save(Book book) {
		if (book.getId() == null) {
			em.persist(book);
			return book;
		} else {
			return em.merge(book);
		}
	}

	@Override
	public Book findById(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		return em.createQuery("select b from Book b INNER JOIN FETCH b.author a INNER JOIN FETCH b.genre g", Book.class).getResultList();
	}

	@Override
	public void deleteById(Long id) {
		em.createQuery("delete from Book b where b.id=:id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Book findByName(String name) {
		return em.createQuery("select b from Book b where b.name=:name", Book.class).setParameter("name", name)
				.getSingleResult();
	}

	@Override
	public List<Book> findByAuthor(String author) {
		return em.createQuery("select b from Book b where b.author.name = :author", Book.class).setParameter("author", author)
				.getResultList();
	}

	@Override
	public List<Book> findByGenre(String genre) {
		return em.createQuery("select b from Book b where b.genre.name = :genre", Book.class).setParameter("genre", genre)
				.getResultList();
	}
}
