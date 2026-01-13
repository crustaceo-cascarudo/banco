package com.fpmislata.banco.spring;

import com.fpmislata.banco.domain.repository.BankAccountRepository;
import com.fpmislata.banco.domain.repository.BankMovementRepository;
import com.fpmislata.banco.domain.repository.CreditCardRepository;
import com.fpmislata.banco.domain.repository.UserRepository;
import com.fpmislata.banco.domain.service.BankAccountService;
import com.fpmislata.banco.domain.service.BankMovementService;
import com.fpmislata.banco.domain.service.CreditCardService;
import com.fpmislata.banco.domain.service.UserService;
import com.fpmislata.banco.domain.service.impl.BankAccountServiceImpl;
import com.fpmislata.banco.domain.service.impl.BankMovementServiceImpl;
import com.fpmislata.banco.domain.service.impl.CreditCardServiceImpl;
import com.fpmislata.banco.domain.service.impl.UserServiceImpl;
import com.fpmislata.banco.infrastructure.PasswordEncoderImpl;
import com.fpmislata.banco.persistence.dao.BankAccountDao;
import com.fpmislata.banco.persistence.dao.BankMovementDao;
import com.fpmislata.banco.persistence.dao.CreditCardDao;
import com.fpmislata.banco.persistence.dao.UserDao;
import com.fpmislata.banco.persistence.dao.impl.BankAccountDaoJpa;
import com.fpmislata.banco.persistence.dao.impl.BankMovementDaoJpa;
import com.fpmislata.banco.persistence.dao.impl.CreditCardDaoJpa;
import com.fpmislata.banco.persistence.dao.impl.UserDaoJpa;
import com.fpmislata.banco.persistence.repository.impl.BankAccountRepositoryImpl;
import com.fpmislata.banco.persistence.repository.impl.BankMovementRepositoryImpl;
import com.fpmislata.banco.persistence.repository.impl.CreditCardRepositoryImpl;
import com.fpmislata.banco.persistence.repository.impl.UserRepositoryImpl;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.fpmislata.banco.persistence.dao.impl.entity")
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

    // BankAccount DAO Bean

    @Bean
    public BankAccountDao bankAccountDao(){
        return new BankAccountDaoJpa();
    }

    @Bean
    public BankAccountRepository bankAccountRepository(BankAccountDao bankAccountDao){
        return new BankAccountRepositoryImpl(bankAccountDao);
    }

    @Bean
    public BankAccountService bankAccountService(BankAccountRepository bankAccountRepository){
        return new BankAccountServiceImpl(bankAccountRepository);
    }

    // ----------------------------------------------

    // BankMovement DAO Bean

    @Bean
    public BankMovementDao bankMovementDao(){
        return new BankMovementDaoJpa();
    }

    @Bean
    public BankMovementRepository bankMovementRepository(BankMovementDao bankMovementDao){
        return new BankMovementRepositoryImpl(bankMovementDao);
    }

    @Bean
    public BankMovementService bankMovementService(BankMovementRepository bankMovementRepository){
        return new BankMovementServiceImpl(bankMovementRepository);
    }

    // ----------------------------------------------

    // CreditCard DAO Bean

    @Bean
    public CreditCardDao creditCardDao(){
        return new CreditCardDaoJpa();
    }

    @Bean
    public CreditCardRepository creditCardRepository(CreditCardDao creditCardDao){
        return new CreditCardRepositoryImpl(creditCardDao);
    }

    @Bean
    public CreditCardService creditCardService(CreditCardRepository creditCardRepository){
        return new CreditCardServiceImpl(creditCardRepository);
    }
}