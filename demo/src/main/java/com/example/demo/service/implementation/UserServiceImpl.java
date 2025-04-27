package com.example.demo.service.implementation;


import com.example.demo.persistence.dao.interfaces.IUserDAO;
import com.example.demo.persistence.entity.UserEntity;
import com.example.demo.presentation.dto.UserDTO;
import com.example.demo.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<UserDTO> getAll() {
        //converir user DTO a userEntity
        ModelMapper modelMapper = new ModelMapper();
        return this.userDAO.getAll()
                .stream()
                //devuelvo una lista de user de la bdd
                //y lo convierto a una lista de userDTO
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getById(Long id) {
       Optional<UserEntity> userEntity=  this.userDAO.getById(id);
        if(userEntity.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            UserEntity currentuser = userEntity.get();
            return modelMapper.map(currentuser, UserDTO.class);
        } else {
            return new UserDTO();
        }
    }

    @Override
    public UserDTO create(UserDTO userDTO) {

        try{
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            //guardo el usuario en la bdd
            this.userDAO.save(userEntity);
            return userDTO;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        //verificar que usuario existe
        Optional<UserEntity> userEntity= this.userDAO.getById(id);

        if(userEntity.isPresent()){
            UserEntity currentuser = userEntity.get();
            currentuser.setName(userDTO.getName());
            currentuser.setLastName(userDTO.getLastName());
            currentuser.setEmail(userDTO.getEmail());
            currentuser.setAge(userDTO.getAge());

            this.userDAO.update(currentuser);

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(currentuser, UserDTO.class);
        }
        //si no existe
        else {
            throw new IllegalArgumentException("Error al actualizar el usuario porque no existe");

        }
    }

    @Override
    public String delete(Long id) {
        Optional<UserEntity> userEntity=  this.userDAO.getById(id);
        if(userEntity.isPresent()) {
            UserEntity currentuser = userEntity.get();
            this.userDAO.delete(currentuser);
            return "Usuario con ID " + id + " ha sido eliminado correctamente";
        } else {
            return "Usuario con ID " + id + " no existe";
        }
    }
}
