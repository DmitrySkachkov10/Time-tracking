package by.krainet.dmitry_skachkov.timerackerservice.repo;

import by.krainet.dmitry_skachkov.timerackerservice.entity.Record;
import by.krainet.dmitry_skachkov.timerackerservice.entity.RecordID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecordRepo extends JpaRepository<Record, RecordID> {

    @EntityGraph(attributePaths = {"task"})
    Optional<Record> findByUserUuidAndTaskUuid(UUID userUuid, UUID taskUuid);

    @EntityGraph(attributePaths = {"task"})
    List<Record> findAll();

}
