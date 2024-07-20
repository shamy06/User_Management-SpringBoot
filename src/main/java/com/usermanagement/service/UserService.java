package com.usermanagement.service;

import java.util.List;

import com.usermanagement.dto.UserDto;
import com.usermanagement.entity.User;

public interface UserService {

	UserDto creatUser (UserDto user);
	
    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
