package by.krainet.dmitry_skachkov.timerackerservice.controller;

import by.krainet.dmitry_skachkov.timerackerservice.core.dto.RecordDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.RecordCompleteDto;
import by.krainet.dmitry_skachkov.timerackerservice.service.api.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<Void> createRecord(@RequestParam UUID taskUuid) {
        recordService.createRecord(taskUuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/task/{taskUuid}/user/{userUuid}")
    public ResponseEntity<RecordDto> getRecordByUuid(@PathVariable UUID taskUuid, @PathVariable UUID userUuid) {

        RecordDto recordDto = recordService.readByUuid(taskUuid, userUuid);
        return ResponseEntity.ok(recordDto);
    }

    @GetMapping
    public ResponseEntity<List<RecordDto>> getAllRecords() {
        List<RecordDto> recordDtos = recordService.readAll();
        return ResponseEntity.ok(recordDtos);
    }

    @PutMapping("/complete")
    public ResponseEntity<Void> completeRecord(@RequestBody RecordCompleteDto completeDto) {
        recordService.end(completeDto);
        return ResponseEntity.ok().build();
    }
}
