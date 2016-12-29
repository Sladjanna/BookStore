package app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import app.model.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
	Page<Book> findByAuthorsFirstNameOrAuthorsLastName(String firstName, String lastName, Pageable page);
}
