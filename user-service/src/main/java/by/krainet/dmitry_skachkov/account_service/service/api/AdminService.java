package by.krainet.dmitry_skachkov.account_service.service.api;

import by.krainet.dmitry_skachkov.account_service.core.dto.PageOfUsers;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserCreate;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserDto;

import java.util.UUID;

public interface AdminService {
    void create(UserCreate userCreate);

    UserDto getUser(UUID uuid);

    PageOfUsers getUsers(int page, int size);

    void update(UserCreate userCreate, UUID uuid, long lastUpdate);
}
