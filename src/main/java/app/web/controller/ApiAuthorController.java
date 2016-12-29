package app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.model.Author;
import app.service.AuthorService;

@RestController
@RequestMapping(value = "/api/authors")
public class ApiAuthorController {
	
	@Autowired
	AuthorService authorService;
	
//	@Autowired
//	private AuthorDTOToAuthor toAuthor;
//
//	@Autowired
//	private AuthorToAuthorDTO toDto;

/*GET AUTHORS*/
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Author>> getAuthors(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page) {

		List<Author> authors = authorService.findAll(page).getContent();

		return new ResponseEntity<>(authors, HttpStatus.OK);
	}
	
}
