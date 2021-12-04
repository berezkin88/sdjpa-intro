package person.birch.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import person.birch.sdjpaintro.domain.Book;
import person.birch.sdjpaintro.repositories.BookRepository;

@Component
@Profile({"local", "default"})
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        var bookDDD = new Book("Domain Driven Design", "1234", "RandomHouse", null);
        var bookSIP = new Book("Spring in Action", "2345", "Oriely", null);

        bookRepository.save(bookDDD);
        bookRepository.save(bookSIP);

        bookRepository.findAll().forEach(book -> System.out.println("Book id: " + book.getId()));

    }
}
