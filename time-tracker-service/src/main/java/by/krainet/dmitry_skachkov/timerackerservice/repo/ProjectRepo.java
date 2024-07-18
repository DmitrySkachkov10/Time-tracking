package by.krainet.dmitry_skachkov.timerackerservice.repo;

import by.krainet.dmitry_skachkov.timerackerservice.entity.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepo extends JpaRepository<Project, UUID> {
    @EntityGraph(attributePaths = {"tasks"})
    Optional<Project> findById(UUID id);

    @EntityGraph(attributePaths = {"tasks"})
    List<Project> findAll();
}
