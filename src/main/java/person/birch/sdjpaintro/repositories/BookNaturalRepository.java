package person.birch.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import person.birch.sdjpaintro.domain.BookNatural;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
