package person.birch.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import person.birch.sdjpaintro.domain.composite.AuthorEmbedded;
import person.birch.sdjpaintro.domain.composite.NameId;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
