package ru.otus.spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Genre;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long count() {
		return (Long) em.createQuery("select count(g) from Genre g").getSingleResult();
	}

	@Override
	public Genre save(Genre genre) {
		if (genre.getId() == null) {
			em.persist(genre);
			return genre;
		} else {
			return em.merge(genre);
		}
	}

	@Override
	public Genre findById(Long id) {
		return em.find(Genre.class, id);
	}

	@Override
	public List<Genre> findAll() {
		return em.createQuery("select g from Genre g", Genre.class).getResultList();
	}

	@Override
	public void deleteById(Long id) {
		em.createQuery("delete from Genre g where g.id=:id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Genre findByName(String name) {
		return em.createQuery("select g from Genre g where g.name = :name", Genre.class).setParameter("name", name)
				.getSingleResult();
	}
}