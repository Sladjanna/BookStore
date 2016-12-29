package app.service;

import org.springframework.data.domain.Page;

import app.model.Book;

public interface BookService {
	Book save(Book book);
	Book findOne(Long id);
	Page<Book> findByAuthor(String firstName, String lastName, int page);
	Page<Book> findAll(int page);
	Book delete(Long id);
}
