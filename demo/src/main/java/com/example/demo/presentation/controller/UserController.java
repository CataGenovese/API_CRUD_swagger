package com.example.demo.presentation.controller;

import com.example.demo.presentation.dto.UserDTO;
import com.example.demo.service.interfaces.IUserService;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("postgres")
public class UserController {

    @Autowired
    private IUserService userService;


    //Get All
    @GetMapping("/get")
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    //Get by Id
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.getById(id), HttpStatus.OK);
    }

    //Create user
    //para crear un usuario ->
    // frontend envia info a controller y convierte el json a un objeto (DTO)
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(this.userService.create(userDTO), HttpStatus.CREATED);
    }

    //Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        return new ResponseEntity<>(this.userService.update(userDTO, id), HttpStatus.OK);

    }

    //Delete User - User deleted
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.delete(id), HttpStatus.NO_CONTENT);
    }
}
