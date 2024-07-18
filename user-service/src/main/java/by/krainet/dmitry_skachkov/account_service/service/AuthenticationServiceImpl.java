package by.krainet.dmitry_skachkov.account_service.service;

import by.krainet.dmitry_skachkov.account_service.core.dto.UserDto;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserLogin;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserRegistration;
import by.krainet.dmitry_skachkov.account_service.core.utils.JwtTokenHandler;
import by.krainet.dmitry_skachkov.account_service.core.utils.UserSecurity;
import by.krainet.dmitry_skachkov.account_service.repo.entity.Role;
import by.krainet.dmitry_skachkov.account_service.repo.entity.UserEntity;
import by.krainet.dmitry_skachkov.account_service.service.api.AuthenticationService;
import by.krainet.dmitry_skachkov.account_service.service.api.UserService;

import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final JwtTokenHandler tokenHandler;

    public AuthenticationServiceImpl(UserService userService, JwtTokenHandler tokenHandler) {
        this.userService = userService;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public String logIn(UserLogin userLogin) {
        UserEntity user = userService.logIn(userLogin);
        return tokenHandler.generateAccessToken(new UserSecurity(user.getUuid().toString(), user.getRole().toString()));
    }

    @Override
    @Transactional
    public void register(UserRegistration userRegistration) {

        userService.save(
                new UserEntity(UUID.randomUUID(),
                        BCrypt.hashpw(userRegistration.getPassword(), BCrypt.gensalt()),
                        userRegistration.getMail(),
                        userRegistration.getFullName(),
                        Role.USER,
                        1
                ));
    }

    @Override
    public UserDto myInfo() {
        UserSecurity userSecurity = (UserSecurity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        UserEntity userEntity = userService.getUserByUuid(UUID.fromString(userSecurity.getUuid()));

        return new UserDto(userEntity.getUuid().toString(),
                userEntity.getMail(),
                userEntity.getFullName(),
                userEntity.getRole().toString(),
                userEntity.getVersion());
    }
}
