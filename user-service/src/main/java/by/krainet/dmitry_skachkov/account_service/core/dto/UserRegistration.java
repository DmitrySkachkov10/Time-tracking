package by.krainet.dmitry_skachkov.account_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistration {

    private String password;

    private String mail;

    private String fullName;

}
