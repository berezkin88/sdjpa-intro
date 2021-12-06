package person.birch.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import person.birch.sdjpaintro.domain.AuthorUuid;
import person.birch.sdjpaintro.domain.BookUuid;
import person.birch.sdjpaintro.repositories.AuthorUuidRepository;
import person.birch.sdjpaintro.repositories.BookRepository;
import person.birch.sdjpaintro.repositories.BookUuidRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ComponentScan(basePackages = {"person.birch.sdjpaintro.bootstrap"})
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MySqlIntegrationTest {

    @Autowired
    BookRepository bookRepository;
    @SpyBean
    @Autowired
    BookUuidRepository bookUuidRepository;
    @SpyBean
    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Test
    void testJpaTestSpliceTransaction() {
        var countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }

    @Test
    void testUuidForBookEntity() {
        var bookToSave = new BookUuid();
        var savedBook = bookUuidRepository.save(bookToSave);

        verify(bookUuidRepository, atLeastOnce()).save(bookToSave);
        assertThat(savedBook)
            .extracting(BookUuid::getId)
            .isNotNull();
    }

    @Test
    void testUuidForAuthorEntity() {
        var authorToSave = new AuthorUuid();
        var savedAuthor = authorUuidRepository.save(authorToSave);

        verify(authorUuidRepository, atLeastOnce()).save(authorToSave);
        assertThat(savedAuthor)
            .extracting(AuthorUuid::getId)
            .isNotNull();
    }
}
