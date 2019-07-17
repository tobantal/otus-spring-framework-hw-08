package ru.otus.spring.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class GenreServiceImpl implements GenreService {

	private final GenreRepository genreRepository;

	@Override
	public Genre createIfItIsNecessaryAndGet(String genre) {
		try {
			return genreRepository.findByName(genre);			
		} catch(EmptyResultDataAccessException e) {
			return genreRepository.save(new Genre(null, genre));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public long size() {
		return genreRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		genreRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

}
