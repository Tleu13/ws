package com.tleu.wsjavathree.service.implementation;

import com.tleu.wsjavathree.service.UserService;
import com.tleu.wsjavathree.model.UserEntity;
import com.tleu.wsjavathree.repository.UserRepository;
import com.tleu.wsjavathree.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto user) {
        if(userRepository.findByEmail(user.getEmail())!= null){
            throw new RuntimeException("Already exist");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setEncryptedPassword("test");
        userEntity.setUserId("testUserId");
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnValue);
        return returnValue;
    }
}
