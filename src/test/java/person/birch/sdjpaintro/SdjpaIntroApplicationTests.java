package person.birch.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import person.birch.sdjpaintro.repositories.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SdjpaIntroApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testBookRepository() {
        var count = bookRepository.count();

        assertThat(count)
            .isPositive();
    }

    @Test
	void contextLoads() {
	}

}
