package com.pj.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.blog.dto.UserDto;
import com.pj.blog.entities.User;
import com.pj.blog.exceptions.ResourceNotFoundException;
import com.pj.blog.repositories.UserRepo;
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDto createUser(UserDto userDto){
        User user= dtoToUser(userDto);
        return userToDto(userRepo.save(user));

    }

    public UserDto updateUser(UserDto userDto,Integer userId){
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException( userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = userRepo.save(user);
	    return userToDto(updatedUser);
    }
    public UserDto getUserById(Integer userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(userId));

		return userToDto(user);
	}

    public List<UserDto> getAllUsers() {

		List<User> users = userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}


	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(userId));
		this.userRepo.delete(user);

	}

    private User dtoToUser(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        return user;
    }

    private UserDto userToDto(User user){
        UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
    
    
}
