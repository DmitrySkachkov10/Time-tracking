package by.krainet.dmitry_skachkov.account_service.service.api;

import by.krainet.dmitry_skachkov.account_service.core.dto.UserDto;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserLogin;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserRegistration;

public interface AuthenticationService {
    String logIn(UserLogin userLogin);

    void register(UserRegistration userRegistration);

    UserDto myInfo();

}
