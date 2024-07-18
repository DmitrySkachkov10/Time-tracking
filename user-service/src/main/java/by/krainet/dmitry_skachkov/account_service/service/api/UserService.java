package by.krainet.dmitry_skachkov.account_service.service.api;

import by.krainet.dmitry_skachkov.account_service.core.dto.PageOfUsers;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserLogin;
import by.krainet.dmitry_skachkov.account_service.repo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserEntity getUserByUuid(UUID uuid);

    Page<UserEntity> getUsers(Pageable pageable);

    void save(UserEntity user);

    UserEntity logIn(UserLogin userLogin);

    void update(UserEntity userEntity);

}
