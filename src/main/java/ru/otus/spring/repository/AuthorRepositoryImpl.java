package ru.otus.spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Author;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long count() {
		return (Long) em.createQuery("select count(a) from Author a").getSingleResult();
	}

	@Override
	public Author save(Author author) {
		if (author.getId() == null) {
			em.persist(author);
			return author;
		} else {
			return em.merge(author);
		}
	}

	@Override
	public Author findById(Long id) {
		return em.find(Author.class, id);
	}

	@Override
	public List<Author> findAll() {
		return em.createQuery("select a from Author a", Author.class).getResultList();
	}

	@Override
	public void deleteById(Long id) {
		em.createQuery("delete from Author a where a.id=:id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Author findByName(String name) {
		return em.createQuery("select a from Author a where a.name=:name", Author.class).setParameter("name", name)
				.getSingleResult();
	}

}
