package person.birch.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import person.birch.sdjpaintro.domain.Book;
import person.birch.sdjpaintro.repositories.BookRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var bookDDD = new Book("Domain Driven Design", "1234", "RandomHouse");
        var bookSIP = new Book("Spring in Action", "2345", "Oriely");

        bookRepository.save(bookDDD);
        bookRepository.save(bookSIP);

        bookRepository.findAll().forEach(book -> System.out.println("Book id: " + book.getId()));

    }
}
