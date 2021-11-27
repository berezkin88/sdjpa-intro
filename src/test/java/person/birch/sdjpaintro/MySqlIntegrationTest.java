package person.birch.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import person.birch.sdjpaintro.repositories.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackages = {"person.birch.sdjpaintro.bootstrap"})
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MySqlIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testJpaTestSpliceTransaction() {
        var countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }
}
