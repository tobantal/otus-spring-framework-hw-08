package ru.otus.spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Comment;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Comment save(Comment comment) {
		if (comment.getId() == null) {
			em.persist(comment);
			return comment;
		} else {
			return em.merge(comment);
		}
	}

	@Override
	public List<Comment> findAllByBookId(Long bookId) {
		return em.createQuery("select c from Comment c where c.book.id = :bookId", Comment.class).setParameter("bookId", bookId)
				.getResultList();
	}

	@Override
	public void deleteById(Long id) {
		em.createQuery("delete from Comment c where c.id=:id").setParameter("id", id).executeUpdate();
	}

}
