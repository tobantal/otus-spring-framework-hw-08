package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

public interface CommentService {
	
	Comment addComment(Comment comment);
	
	List<Comment> getCommentsForBook(Book book);
	
	void removeComment(Long id);

}
