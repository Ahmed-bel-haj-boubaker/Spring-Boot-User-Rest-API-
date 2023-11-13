package com.springboot.springbootresttfulwebservice.mapper;

import com.springboot.springbootresttfulwebservice.dto.UserDto;
import com.springboot.springbootresttfulwebservice.entity.User;

import java.util.List;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }


}
