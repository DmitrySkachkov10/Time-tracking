package by.krainet.dmitry_skachkov.account_service.core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreate {

    private String password;

    private String mail;

    private String fullName;

    private String role;

}
