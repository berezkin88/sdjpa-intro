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
import person.birch.sdjpaintro.domain.BookNatural;
import person.birch.sdjpaintro.domain.BookUuid;
import person.birch.sdjpaintro.domain.composite.AuthorComposite;
import person.birch.sdjpaintro.domain.composite.NameId;
import person.birch.sdjpaintro.repositories.*;

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
    @Autowired
    BookNaturalRepository bookNaturalRepository;
    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

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

    @Test
    void bookNaturalTest() {
        var bookNatural = new BookNatural();
        bookNatural.setTitle("My book");

        var savedBook = bookNaturalRepository.save(bookNatural);

        var fetchedBook = bookNaturalRepository.getById(savedBook.getTitle());
        assertThat(fetchedBook)
            .isNotNull();
    }

    @Test
    void authorCompositeTest() {
        var nameId = new NameId("Alex", "B");
        var authorComposite = new AuthorComposite();
        authorComposite.setCountry("UA");
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());

        var savedAuthor = authorCompositeRepository.save(authorComposite);
        assertThat(savedAuthor).isNotNull();

        var fetched = authorCompositeRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }
}
