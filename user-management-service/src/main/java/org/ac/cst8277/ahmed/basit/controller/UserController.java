package org.ac.cst8277.ahmed.basit.controller;

import org.ac.cst8277.ahmed.basit.domain.dto.UserDTO;
import org.ac.cst8277.ahmed.basit.service.UserService;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.accepted().body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> addUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.accepted().body(userService.createNewUser(userDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> getById(@PathVariable("userId") long userId) {
        return ResponseEntity.accepted().body(userService.getUserByField(userId));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.accepted().body(userService.updateUser(userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable("userId") long userId) {
        return ResponseEntity.accepted().body(userService.deleteUser(userId));
    }

    @GetMapping(value = "/role", params = "rolename")
    public ResponseEntity<BaseResponse> getByRole(@RequestParam("rolename") String rolename) {
        return ResponseEntity.accepted().body(userService.getUserByUserRole(rolename));
    }

    @GetMapping(value = "/", params = "username")
    public ResponseEntity<BaseResponse> getByUsername(@RequestParam("username") String username) {
        return ResponseEntity.accepted().body(userService.getUserByUsername(username));
    }
}
