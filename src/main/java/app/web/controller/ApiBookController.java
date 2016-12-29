package app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.model.Book;
import app.service.BookService;

@RestController
@RequestMapping(value = "/api/books")
public class ApiBookController {
	
	@Autowired
	BookService bookService;
	
//	@Autowired
//	private BookToBookDTO toDTO;
//
//	@Autowired
//	private BookDTOToBook toBook;
	
/*	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<BookDTO>> getBooks(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page) {

		List<Book> books = bookService.findAll(page).getContent();

		return new ResponseEntity<>(toDTO.convert(books), HttpStatus.OK);
	}*/
	
/*GET kada se doda pretrazivanje i filteri */
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Book>> getBooksByAuthor(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "name", required = false) String name) {

		List<Book> books;
		if(name!=null){
			books = bookService.findByAuthor(name, name, page).getContent();
		}
		else{
			books = bookService.findAll(page).getContent();
		}
		
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<Book> getBook(@PathVariable Long id) {
		Book book = bookService.findOne(id);
		if (book == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(book, HttpStatus.OK);
	}
/*SAVE*/	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Book> add(@RequestBody Book newBook) {

		Book savedBook = bookService.save(newBook);

		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}

/*EDIT*/
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<Book> edit(@RequestBody Book book,
			@PathVariable Long id) {

		if (id != book.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Book persisted = bookService.save(book);

		return new ResponseEntity<>(persisted, HttpStatus.OK);
	}
	
/*DELETE*/
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Book> delete(@PathVariable Long id) {
		Book deleted = bookService.delete(id);

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}



	
}
