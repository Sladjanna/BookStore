package app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.model.Author;
import app.model.Book;
import app.model.User;
import app.service.AuthorService;
import app.service.BookService;
import app.service.UserService;

@Component
public class TestData {
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init(){
		for(int t=1; t<10; t++){
		User user = new User();
	    user.setUserName("naziv"+t);
        user.setEmail(t+"moj@gmail.com");
        user.setPassword("123");
   
        userService.save(user);
		}
        
		Author martinHajdeger = new Author("Martin", "Hajdeger");
		authorService.save(martinHajdeger);
		Book bitakIVreme = new Book("Ovo je  JWD16");
		bitakIVreme.getAuthors().add(martinHajdeger);
		bookService.save(bitakIVreme);
		
		Author fridrihNice = new Author("Fridrih", "Nice");
		authorService.save(fridrihNice);
		Book koJeNiceovZaratustra = new Book("Ko je Niceov Zaratustra?");
		koJeNiceovZaratustra.getAuthors().add(martinHajdeger);
		koJeNiceovZaratustra.getAuthors().add(fridrihNice);
		bookService.save(koJeNiceovZaratustra);
		
		Author sorenKjerkegar = new Author("Soren", "Kejrkegar");
		authorService.save(sorenKjerkegar);
		Author markoMiljanovPopovic = new Author("Marko Miljanov", "Popovic");
		authorService.save(markoMiljanovPopovic);
		
		
		Author eHicks = new Author("Ester","Hicks");
		authorService.save(eHicks);
		Author jHicks = new Author("Jerry","Hicks");
		authorService.save(jHicks);
		Author ronda = new Author("Ronda","Burns");
		authorService.save(ronda);
		Author andric = new Author("Ivo", "Andric");
		authorService.save(andric);
		Author jung = new Author("Karl","Jung"); //moze da postoji autor bez knjige
		authorService.save(jung);
		
		Book book1 = new Book("Na drini cuprija");
		book1.getAuthors().add(andric);
		bookService.save(book1);
		
		Book book2 = new Book("Jelena zena koje nema");
		book2.getAuthors().add(andric);
		bookService.save(book2);
		
		Book book3 = new Book("Tajna");
		book3.getAuthors().add(ronda);
		bookService.save(book3);
		
		Book book4 = new Book("Moc");
		book4.getAuthors().add(ronda);
		bookService.save(book4);
		
		Book book5 = new Book("Moc sadasnjeg trenutka");
		book5.getAuthors().add(jHicks);  //ovako dodajemo 2 authora
		book5.getAuthors().add(eHicks); //ovako dodajemo 2 authora
		bookService.save(book5);
		
			for(int i=1; i<10; i++){
			
			Author autor = new Author(("Ime "+i),("Prezime "+i));
			authorService.save(autor);

			
			Book book = new Book(("naziv knjige "+i));
			book.getAuthors().add(autor);
			bookService.save(book);
		}
		
	}
}
