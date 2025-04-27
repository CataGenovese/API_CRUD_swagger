package com.example.demo.persistence.dao.interfaces;

import com.example.demo.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<UserEntity> getAll();

    Optional<UserEntity> getById(Long id);

    void save(UserEntity userEntity);

    void update(UserEntity userEntity);

    void delete(UserEntity userEntity);

}
