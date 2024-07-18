package by.krainet.dmitry_skachkov.timerackerservice.repo;

import by.krainet.dmitry_skachkov.timerackerservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepo extends JpaRepository<Task, UUID> {
}
