package by.krainet.dmitry_skachkov.timerackerservice.service.api;

import by.krainet.dmitry_skachkov.timerackerservice.core.dto.RecordDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.RecordCompleteDto;

import java.util.List;
import java.util.UUID;

public interface RecordService {

    void createRecord(UUID taskUuid);

    RecordDto readByUuid(UUID taskUuid, UUID userUuid);

    List<RecordDto> readAll();

    void end(RecordCompleteDto completeDto);
}


