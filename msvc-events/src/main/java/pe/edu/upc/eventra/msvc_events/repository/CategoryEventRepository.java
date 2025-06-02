package pe.edu.upc.eventra.msvc_events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.msvc_events.model.entities.CategoryEvent;

import java.util.Optional;

public interface CategoryEventRepository extends JpaRepository<CategoryEvent, Long> {
    Optional<CategoryEvent> findByName(String name);
    // Repository methods...
}

