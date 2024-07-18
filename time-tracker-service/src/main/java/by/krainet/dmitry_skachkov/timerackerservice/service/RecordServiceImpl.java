package by.krainet.dmitry_skachkov.timerackerservice.service;

import by.dmitryskachkov.exception.exceptions.ValidationException;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.RecordDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.RecordCompleteDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.utils.UserSecurity;
import by.krainet.dmitry_skachkov.timerackerservice.entity.Record;
import by.krainet.dmitry_skachkov.timerackerservice.entity.RecordID;
import by.krainet.dmitry_skachkov.timerackerservice.entity.Task;
import by.krainet.dmitry_skachkov.timerackerservice.repo.RecordRepo;
import by.krainet.dmitry_skachkov.timerackerservice.service.api.RecordService;
import by.krainet.dmitry_skachkov.timerackerservice.service.api.TaskService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepo recordRepo;

    private final TaskService taskService;

    public RecordServiceImpl(RecordRepo recordRepo, TaskService taskService) {
        this.recordRepo = recordRepo;
        this.taskService = taskService;
    }

    @Override
    @Transactional
    public void createRecord(UUID taskUuid) {
        UserSecurity s = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID userUuid = UUID.fromString(s.getUuid());


        recordRepo.save(new Record(userUuid, taskUuid,
                LocalDateTime.now(),
                null,
                new Task(taskUuid)));
    }

    @Override
    public RecordDto readByUuid(UUID taskUuid, UUID userUuid) {
        Record record = recordRepo.findByUserUuidAndTaskUuid(userUuid, taskUuid)
                .orElseThrow(() -> new ValidationException(""));
        return new RecordDto(record.getTaskUuid().toString(),
                record.getUserUuid().toString(),
                record.getAssignedAt(),
                record.getCompletedAt());
    }

    @Override
    public List<RecordDto> readAll() {
        List<Record> records = recordRepo.findAll();
        return records.stream()
                .map(record -> new RecordDto(
                        record.getTaskUuid().toString(),
                        record.getUserUuid().toString(),
                        record.getAssignedAt(),
                        record.getCompletedAt()
                ))
                .toList();
    }

    @Override
    @Transactional
    public void end(RecordCompleteDto completeDto) {
        Record record = recordRepo.findByUserUuidAndTaskUuid(completeDto.getUserUuid(), completeDto.getTaskUuid())
                .orElseThrow(() -> new ValidationException(""));
        record.setCompletedAt(LocalDateTime.now());
//        record.setTimeSpent(Duration.between(record.getAssignedAt(), record.getCompletedAt()));

        taskService.setStatus(completeDto.getTaskUuid(), completeDto.getStatus());
        recordRepo.save(record);
    }
}
