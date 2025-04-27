package com.example.demo.service.interfaces;

import com.example.demo.presentation.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO getById(Long id);
    UserDTO create(UserDTO userDTO);
    UserDTO update(UserDTO userDTO, Long id);
    String delete(Long id);
}
