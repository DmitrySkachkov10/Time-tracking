package by.krainet.dmitry_skachkov.account_service.controller;


import by.krainet.dmitry_skachkov.account_service.core.dto.PageOfUsers;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserCreate;
import by.krainet.dmitry_skachkov.account_service.core.dto.UserDto;
import by.krainet.dmitry_skachkov.account_service.service.api.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserCreate userCreate) {
        adminService.create(userCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageOfUsers> getUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(adminService.getUsers(page, size), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDto> getUserByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(adminService.getUser(uuid), HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{version}")
    public ResponseEntity<?> updateUser(@PathVariable UUID uuid,
                                        @PathVariable long version,
                                        @RequestBody UserCreate userCreate) {
        adminService.update(userCreate, uuid, version);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
