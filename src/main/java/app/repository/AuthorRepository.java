package app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import app.model.Author;

@Repository
public interface AuthorRepository  extends PagingAndSortingRepository<Author, Long> {

}
