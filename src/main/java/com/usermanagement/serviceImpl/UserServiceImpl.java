package com.usermanagement.serviceImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.usermanagement.dto.UserDto;
import com.usermanagement.entity.User;
import com.usermanagement.exception.EmailAlreadyExistsException;
import com.usermanagement.exception.ResourceNotFoundException;
import com.usermanagement.mapper.AutoUserMapper;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	private ModelMapper modelMapper;

	@Override
	public UserDto creatUser(UserDto userDto) {
//		User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }
        
		User user = AutoUserMapper.mapper.mapToUser(userDto);
		
		User savedUser= userRepository.save(user);	
		
//		 UserDto savedUserDto= modelMapper.map(savedUser, UserDto.class);
		 
		UserDto savedUserDto = AutoUserMapper.mapper.mapToUserDto(savedUser);
		
		return savedUserDto;
		
	}
	
	 @Override
	    public UserDto getUserById(Long userId) {
	        User user = userRepository.findById(userId).orElseThrow(
	        		()-> new ResourceNotFoundException("User","Id",userId)
	        );
	 
	        
//	        return modelMapper.map(user, UserDto.class);
	        
	        return AutoUserMapper.mapper.mapToUserDto(user);
	    }

	    @Override
	    public List<UserDto> getAllUsers() {
	    	List<User> users = userRepository.findAll();
	    	
//	        return users.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
	    
	    	return users.stream().map((user)->AutoUserMapper.mapper.mapToUserDto(user)).collect(Collectors.toList());
	    }

	    @Override
	    public UserDto updateUser(UserDto user) {
	        User existingUser = userRepository.findById(user.getId()).orElseThrow(
	        		()-> new ResourceNotFoundException("user","id",user.getId())
	        		);
	        existingUser.setFirstName(user.getFirstName());
	        existingUser.setLastName(user.getLastName());
	        existingUser.setEmail(user.getEmail());
	        User updatedUser = userRepository.save(existingUser);
//	        return modelMapper.map(updatedUser, UserDto.class);
	        
	        return AutoUserMapper.mapper.mapToUserDto(updatedUser);
	    }

	    @Override
	    public void deleteUser(Long userId) {
	    	User existingUser = userRepository.findById(userId).orElseThrow(
	        		()-> new ResourceNotFoundException("user","id",userId)
	        		);
	        userRepository.deleteById(existingUser.getId());
	    }
	
	
}
