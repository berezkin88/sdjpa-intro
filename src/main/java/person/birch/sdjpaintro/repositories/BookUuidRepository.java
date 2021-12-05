package person.birch.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import person.birch.sdjpaintro.domain.BookUuid;

import java.util.UUID;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {
}
