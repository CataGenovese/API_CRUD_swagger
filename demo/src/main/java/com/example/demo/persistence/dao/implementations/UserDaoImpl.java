package com.example.demo.persistence.dao.implementations;

import com.example.demo.persistence.dao.interfaces.IUserDAO;
import com.example.demo.persistence.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements IUserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {
        return this.em.createQuery("SELECT u from UserEntity u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getById(Long id) {
        return Optional.ofNullable(this.em.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public void save(UserEntity userEntity) {
        this.em.persist(userEntity);
        this.em.flush();
    }

    @Override
    @Transactional
    public void update(UserEntity userEntity) {
        this.em.merge(userEntity);
    }

    @Override
    @Transactional
    public void delete(UserEntity userEntity) {
        this.em.remove(userEntity);
    }
}
