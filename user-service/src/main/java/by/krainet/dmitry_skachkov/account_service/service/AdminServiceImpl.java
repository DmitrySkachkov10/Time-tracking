package by.krainet.dmitry_skachkov.account_service.service;

import by.krainet.dmitry_skachkov.account_service.core.dto.PageOfUsers;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserCreate;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserDto;
import by.krainet.dmitry_skachkov.account_service.repo.entity.Role;
import by.krainet.dmitry_skachkov.account_service.repo.entity.UserEntity;
import by.krainet.dmitry_skachkov.account_service.service.api.AdminService;
import by.krainet.dmitry_skachkov.account_service.service.api.UserService;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    public AdminServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public void create(UserCreate userCreate) {
        userService.save(new UserEntity(UUID.randomUUID(),
                BCrypt.hashpw(userCreate.getPassword(), BCrypt.gensalt()),
                userCreate.getMail(),
                userCreate.getFullName(),
                Role.valueOf(userCreate.getRole()),
                1
        ));
    }

    @Override
    public UserDto getUser(UUID uuid) {
        UserEntity userEntity = userService.getUserByUuid(uuid);
        return new UserDto(userEntity.getUuid().toString(),
                userEntity.getMail(),
                userEntity.getFullName(),
                userEntity.getRole().toString(),
                userEntity.getVersion());
    }

    @Override
    public PageOfUsers getUsers(int page, int size) {
        Page<UserEntity> userEntities = userService.getUsers(PageRequest.of(page - 1, size));


        return new PageOfUsers(userEntities.getNumber(),
                userEntities.getSize(),
                userEntities.getTotalPages(),
                userEntities.getTotalElements(),
                userEntities.isFirst(),
                userEntities.getNumberOfElements(),
                userEntities.get()
                        .map(m -> new UserDto(m.getUuid().toString(),
                                m.getMail(),
                                m.getFullName(),
                                m.getRole().toString(),
                                m.getVersion()))
                        .toList());
    }

    @Override
    @Transactional
    public void update(UserCreate userCreate, UUID uuid, long lastUpdate) {
        UserEntity userEntity = new UserEntity(uuid,
                BCrypt.hashpw(userCreate.getPassword(), BCrypt.gensalt()),
                userCreate.getMail(),
                userCreate.getFullName(),
                Role.valueOf(userCreate.getRole()),
                lastUpdate);
        userService.update(userEntity);
    }
}
