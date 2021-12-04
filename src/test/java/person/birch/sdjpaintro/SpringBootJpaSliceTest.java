package person.birch.sdjpaintro;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import person.birch.sdjpaintro.domain.Book;
import person.birch.sdjpaintro.repositories.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackages = {"person.birch.sdjpaintro.bootstrap"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringBootJpaSliceTest {

    @Autowired
    private BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void testJpaTestSplice() {
        var countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("Test book", "122312", "Test publisher", null));

        var countAfter = bookRepository.count();

        assertThat(countAfter)
            .isGreaterThan(countBefore);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        var countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
    }
}
