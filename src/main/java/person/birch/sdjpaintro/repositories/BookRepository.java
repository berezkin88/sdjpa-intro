package person.birch.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import person.birch.sdjpaintro.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
