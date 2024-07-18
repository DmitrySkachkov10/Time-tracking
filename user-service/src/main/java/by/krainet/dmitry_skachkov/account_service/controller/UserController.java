package by.krainet.dmitry_skachkov.account_service.controller;

import by.krainet.dmitry_skachkov.account_service.core.dto.UserDto;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserLogin;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserRegistration;
import by.krainet.dmitry_skachkov.account_service.service.api.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRegistration userRegistration) {
        authenticationService.register(userRegistration);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        String token = authenticationService.logIn(userLogin);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getInfo() {
        return new ResponseEntity<>(authenticationService.myInfo(), HttpStatus.OK);
    }
}
