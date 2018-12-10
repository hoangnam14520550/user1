package com.dxc.user.service;

import com.dxc.user.api.model.User;
import com.dxc.user.common.StorageError;
import com.dxc.user.entity.UserEntity;
import com.dxc.user.exception.StorageException;
import com.dxc.user.repository.UserRepository;
import com.dxc.user.service.validator.EmailValidator;
import com.dxc.user.service.validator.UsernameValidator;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private UsernameValidator usernameValidator;

    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntity.class);

    @Transactional
    public String upsertUser(User user) {
        LOGGER.info(user.toString());
        emailValidator.validateEmail(user.getEmail());
        usernameValidator.validateUsername(user.getUsername());
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        if (userEntity == null ) {
            return createAccount(user);
        }
        String id = userEntity.getId();
        Date createDate = userEntity.getCreateDate();
        userEntity = user2UserEntity(user);
        userEntity.setId(id);
        userEntity.setCreateDate(createDate);
        userRepository.saveAndFlush(userEntity);
        return "Updated " + userEntity.getId();
    }

    @Transactional
    public String deleteUser(String username) {
        usernameValidator.validateUsername(username);
        int count = userRepository.deleteByUsername(username);
        if (count <= 0)
            throw new StorageException(StorageError.USER_NOT_FOUND, username);
        return "Deleted " + username;
    }

    public User getUserByUsername(String username) {
        usernameValidator.validateUsername(username);
        UserEntity userEntity = userRepository.findByUsernameAndDeleted(username, false);
        if (userEntity == null) throw new StorageException(StorageError.USER_NOT_FOUND, username);
        return userEntity2User(userEntity);
    }

    public String loginUser(String username, String password) {
        UserEntity userEntity = userRepository.findByUsernameAndDeleted(username, false);
        if (userEntity == null) throw new StorageException(StorageError.USER_NOT_FOUND, username);
        if (userRepository.findByUsernameAndPasswordAndDeleted(username, password, false) != null)
            return "Login successfully";
        else return "Login failed";
    }


    @Transactional
    private String createAccount(User user) {
        String id = UUID.randomUUID().toString();
        UserEntity userEntity = user2UserEntity(user);
        userEntity.setId(id);
        userEntity.setDeleted(false);
        userEntity.setCreateDate(new Date());
        userRepository.saveAndFlush(userEntity);
        return "Created "+id;
    }

    private User userEntity2User(UserEntity userEntity) {
        User user = new User();
        user.setDob(new LocalDate(userEntity.getDob()));
        user.setEmail(userEntity.getEmail());
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setRole(userEntity.getRole());
        return user;
    }

    private UserEntity user2UserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setDob(user.getDob().toDate());
        userEntity.setModifiedDate(new Date());
        userEntity.setRole(user.getRole());
        return userEntity;
    }

}
