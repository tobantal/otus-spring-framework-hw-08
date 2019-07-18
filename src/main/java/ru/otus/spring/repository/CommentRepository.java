package ru.otus.spring.repository;

import java.util.List;

import ru.otus.spring.domain.Comment;

public interface CommentRepository {

	Comment save(Comment comment);
	
	List<Comment> findAllByBookId(Long book_id);
	
	void deleteById(Long id);
	
}
