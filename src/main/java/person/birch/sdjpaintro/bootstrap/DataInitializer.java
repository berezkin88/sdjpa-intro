package person.birch.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import person.birch.sdjpaintro.domain.AuthorUuid;
import person.birch.sdjpaintro.domain.Book;
import person.birch.sdjpaintro.repositories.AuthorUuidRepository;
import person.birch.sdjpaintro.repositories.BookRepository;

@Component
@Profile({"local", "default"})
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        var bookDDD = new Book("Domain Driven Design", "1234", "RandomHouse", null);
        var bookSIP = new Book("Spring in Action", "2345", "Oriely", null);

        bookRepository.save(bookDDD);
        bookRepository.save(bookSIP);

        bookRepository.findAll().forEach(book -> System.out.println("Book id: " + book.getId()));

        var authorUuid = new AuthorUuid();
        authorUuid.setFirstName("Joe");
        authorUuid.setLastName("Buck");
        var authorSaved = authorUuidRepository.save(authorUuid);
        System.out.printf("Saved author UUID: %s", authorSaved.getId());
    }
}
