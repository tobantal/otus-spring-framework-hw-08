package ru.otus.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.CommentRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository commentRepository;

	@Override
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsForBook(Book book) {
		return commentRepository.findAllByBookId(book.getId());
	}

	@Override
	public void removeComment(Long id) {
		commentRepository.deleteById(id);
	}

}
