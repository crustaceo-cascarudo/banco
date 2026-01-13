package com.fpmislata.banco.persistence.repository.impl;

import com.fpmislata.banco.domain.repository.UserRepository;
import com.fpmislata.banco.domain.repository.entity.UserEntity;
import com.fpmislata.banco.persistence.dao.UserDao;
import com.fpmislata.banco.persistence.dao.impl.entity.UserJpaEntity;
import com.fpmislata.banco.persistence.repository.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        UserJpaEntity jpaEntity = userDao.findById(id).orElse(null);
        UserEntity entity = UserMapper.getInstance().fromUserJpaEntitytoUserEntity(jpaEntity);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<UserEntity> findByDni(String dni) {
        List<UserJpaEntity> jpaEntities = userDao.findByDni(dni).
                stream().toList();
        if (jpaEntities.isEmpty()) {
            return List.of();
        }
        return jpaEntities.stream()
                .map(UserMapper.getInstance()::fromUserJpaEntitytoUserEntity)
                .toList();
    }

    @Override
    public UserEntity logByDni(String dni) {
        List<UserJpaEntity> jpaEntities = userDao.findByDni(dni).
                stream().toList();
        if (jpaEntities.isEmpty()) {
            return null;
        }
        return UserMapper.getInstance().fromUserJpaEntitytoUserEntity(jpaEntities.get(0));
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        UserJpaEntity jpaEntity = UserMapper.getInstance().fromUserEntitytoJpaEntity(userEntity);        
        if (userEntity.id() != null) {
            UserJpaEntity existingEntity = userDao.findById(userEntity.id()).orElse(null);
            if (existingEntity != null) {
                userDao.update(jpaEntity);
                return userEntity;
            }
        }
        return UserMapper.getInstance().fromUserJpaEntitytoUserEntity(
                userDao.insert(jpaEntity));
    }

    @Override
    public String createSessionToken(Long userId) {
        return userDao.createSessionToken(userId);
    }

    @Override
    public UserEntity findByToken(String token) {
        UserJpaEntity jpaEntity = userDao.findByToken(token);
        return UserMapper.getInstance().fromUserJpaEntitytoUserEntity(jpaEntity);
    }

    @Override
    public void deleteSessionToken(String token) {
        userDao.deleteToken(token);
    }

}
