package com.fpmislata.banco.spring;

import com.fpmislata.banco.domain.repository.UserRepository;
import com.fpmislata.banco.domain.service.UserService;
import com.fpmislata.banco.domain.service.impl.UserServiceImpl;
import com.fpmislata.banco.infrastructure.PasswordEncoderImpl;
import com.fpmislata.banco.persistence.dao.UserDao;
import com.fpmislata.banco.persistence.dao.impl.UserDaoJpa;
import com.fpmislata.banco.persistence.repository.impl.UserRepositoryImpl;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.fpmislata.back.persistence.dao.impl.entity")
public class SpringConfig {

    // User DAO Bean

    @Bean
    public UserDao userDao() {
        return new UserDaoJpa();
    }

    @Bean
    public UserRepository userRepository(UserDao userDao) {
        return new UserRepositoryImpl(userDao);
    }

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoderImpl passwordEncoderImpl) {
        return new UserServiceImpl(userRepository, passwordEncoderImpl);
    }

    @Bean
    public PasswordEncoderImpl passwordEncoderImpl() {
        return new PasswordEncoderImpl();
    }

    // ----------------------------------------------


}