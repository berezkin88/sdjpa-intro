package person.birch.sdjpaintro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import person.birch.sdjpaintro.domain.Book;
import person.birch.sdjpaintro.repositories.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringBootJpaSliceTest {

    @Autowired
    private BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void testJpaTestSplice() {
        var countBefore = bookRepository.count();
        assertThat(countBefore).isZero();

        bookRepository.save(new Book("Test book", "122312", "Test publisher"));

        var countAfter = bookRepository.count();

        assertThat(countAfter)
            .isGreaterThan(countBefore);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        var countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(1L);
    }
}
