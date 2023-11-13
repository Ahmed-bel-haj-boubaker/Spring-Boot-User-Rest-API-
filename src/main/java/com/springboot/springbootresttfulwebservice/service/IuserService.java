package com.springboot.springbootresttfulwebservice.service;


import com.springboot.springbootresttfulwebservice.dto.UserDto;
import com.springboot.springbootresttfulwebservice.entity.User;

import java.util.List;

public interface IuserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long idUSer);
    UserDto updateUser(UserDto user);

    List<UserDto> getAllUsers();

    void deleteUser(Long idUser);



}
