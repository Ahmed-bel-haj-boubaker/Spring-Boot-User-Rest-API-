package com.springboot.springbootresttfulwebservice.service;

import com.springboot.springbootresttfulwebservice.dto.UserDto;
import com.springboot.springbootresttfulwebservice.exception.EmailAlreadyExistsException;
import com.springboot.springbootresttfulwebservice.exception.ResourceNotFoundException;
import com.springboot.springbootresttfulwebservice.mapper.AutoUserMapper;
import com.springboot.springbootresttfulwebservice.mapper.UserMapper;
import com.springboot.springbootresttfulwebservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.springbootresttfulwebservice.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements  IuserService {
 final UserRepository userRepository;

 final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
       Optional<User> userEmail = userRepository.findUserByEmail(userDto.getEmail());
        if (userEmail.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        //Convert UserDto into User jpa entity
        //  User user = UserMapper.mapToUser(userDto);
        //  User user = modelMapper.map(userDto,User.class);

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);


        User savedUser = userRepository.save(user);
        //convert User jpa entity to UserDTo
     //   UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

    //    UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);

        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long idUSer) {
        User optionalUser = userRepository.findById(idUSer).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",idUSer)
        );


        return AutoUserMapper.MAPPER.mapToUserDto(optionalUser);
              //  UserMapper.mapToUserDto(user);
              //modelMapper.map(user,UserDto.class);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user)->AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());

        // return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        // return users.stream().map((user)->modelMapper.map(user , UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {


        // User user = UserMapper.mapToUser(userDto);
       //  User user = modelMapper.map(userDto,User.class);
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                ()->new ResourceNotFoundException("id", "User ",user.getId())
        );

        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        User updatedUser = userRepository.save(existingUser);

       // UserDto UpdateduserDto = UserMapper.mapToUserDto(updatedUser);
       //  UserDto UpdateduserDto = modelMapper.map(updatedUser,UserDto.class);

        UserDto UpdateduserDto = AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
        return UpdateduserDto;
    }

    @Override
    public void deleteUser(Long idUser) {
            Optional<User> user = userRepository.findUserById(idUser);

            if (user.isPresent()){
                userRepository.deleteById(idUser);

            }else {
                throw new ResourceNotFoundException("User","id",idUser);
            }
    }
 /*




*/

}
