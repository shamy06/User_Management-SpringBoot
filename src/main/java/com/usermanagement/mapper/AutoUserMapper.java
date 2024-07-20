package com.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.usermanagement.dto.UserDto;
import com.usermanagement.entity.User;

@Mapper
public interface AutoUserMapper {
	
	AutoUserMapper mapper=Mappers.getMapper(AutoUserMapper.class);
	
	UserDto mapToUserDto(User user);
	
	User mapToUser(UserDto userDto);
}
