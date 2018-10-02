package com.tleu.wsjavathree.controller;


import com.tleu.wsjavathree.service.UserService;
import com.tleu.wsjavathree.model.request.UserDetailsRequestModel;
import com.tleu.wsjavathree.model.response.UserResponse;
import com.tleu.wsjavathree.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {


    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser() {
        return "get user was called";
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserResponse returnValue = new UserResponse();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails,userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser,returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";

    }
    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }

}
