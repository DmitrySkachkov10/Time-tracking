package by.krainet.dmitry_skachkov.timerackerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecordID implements Serializable {


    private UUID userUuid;


    private UUID taskUuid;
}