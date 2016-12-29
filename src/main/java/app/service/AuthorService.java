package app.service;

import org.springframework.data.domain.Page;

import app.model.Author;

public interface AuthorService {
	Author save(Author author);
	Page<Author> findAll(int page);

}
