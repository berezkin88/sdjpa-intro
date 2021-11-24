package person.birch.sdjpaintro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import person.birch.sdjpaintro.domain.Book;
import person.birch.sdjpaintro.repositories.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SpringBootJpaSliceTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testJpaTestSplice() {
        var countBefore = bookRepository.count();

        bookRepository.save(new Book("Test book", "122312", "Test publisher"));

        var countAfter = bookRepository.count();

        assertThat(countAfter)
            .isGreaterThan(countBefore);
    }
}
