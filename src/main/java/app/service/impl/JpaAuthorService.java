package app.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import app.model.Author;
import app.repository.AuthorRepository;
import app.service.AuthorService;

@Service
@Transactional
public class JpaAuthorService implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public Author save(Author author) {
		return authorRepository.save(author);	
	}

	@Override
	public Page<Author> findAll(int page) {
		return authorRepository.findAll(new PageRequest(page, 10));
	}

}
