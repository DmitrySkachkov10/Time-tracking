package by.krainet.dmitry_skachkov.account_service.service;

import by.dmitryskachkov.exception.exceptions.ValidationException;
import by.krainet.dmitry_skachkov.account_service.core.dto.PageOfUsers;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserLogin;
import by.krainet.dmitry_skachkov.account_service.repo.UserRepo;
import by.krainet.dmitry_skachkov.account_service.repo.entity.UserEntity;
import by.krainet.dmitry_skachkov.account_service.service.api.UserService;
import jakarta.transaction.Transactional;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity getUserByUuid(UUID uuid) {
        return userRepo.findById(uuid).orElseThrow(() -> new ValidationException(""));
    }

    @Override
    public Page<UserEntity> getUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(UserEntity user) {

        if (userRepo.findByMail(user.getMail()).isPresent()) {
            throw new ValidationException("User with email " + user.getMail() + " already exists.");
        }

        userRepo.save(user);
    }

    @Override
    public UserEntity logIn(UserLogin userLogin) {
        UserEntity user = userRepo.findByMail(userLogin.getMail())
                .orElseThrow(() -> new ValidationException("Invalid login or password"));

        if (BCrypt.checkpw(userLogin.getPassword(), user.getPassword())) {
            return user;
        }

        throw new ValidationException("Invalid login or password");
    }

    @Override
    @Transactional
    public void update(UserEntity userEntity) {
        try {
            userRepo.save(userEntity);
        } catch (OptimisticEntityLockException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
