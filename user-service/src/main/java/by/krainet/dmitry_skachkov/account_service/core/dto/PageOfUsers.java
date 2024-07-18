package by.krainet.dmitry_skachkov.account_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageOfUsers {

    private int number;

    private int size;

    private int totalPages;

    private long totalElements;

    private boolean first;

    private int numberOfElements;

    private List<UserDto> users = new ArrayList<>();

}
