package ru.otus.spring.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {
	
	private final AuthorRepository authorRepository;

	@Override
	public Author createIfItIsNecessaryAndGet(String author) {		
		try {
			return authorRepository.findByName(author);
		} catch(EmptyResultDataAccessException e) {
			return authorRepository.save(new Author(null, author));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public long size() {
		return authorRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		authorRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Author> findAll() {
		return authorRepository.findAll();
	}

}
