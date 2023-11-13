package com.springboot.springbootresttfulwebservice.controller;

import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
import com.springboot.springbootresttfulwebservice.dto.UserDto;
import com.springboot.springbootresttfulwebservice.entity.User;
import com.springboot.springbootresttfulwebservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

     private UserService userService;

     @PostMapping
     ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
          UserDto savedUser = userService.createUser(user);
          return new  ResponseEntity<>(savedUser,HttpStatus.CREATED);
     }

     @GetMapping("/getById/{idUSer}")
     ResponseEntity<UserDto> getUserByID(@PathVariable Long idUSer){
          UserDto optionalUser= userService.getUserById(idUSer);
          return new ResponseEntity<>(optionalUser,HttpStatus.OK);
     }


     @GetMapping
     ResponseEntity<List<UserDto>> getAllUsers(){
          List<UserDto> users = userService.getAllUsers();
          return new ResponseEntity<>(users,HttpStatus.OK);
     }

     @PutMapping("/updateUser/{idUser}")
     ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto user, @PathVariable Long idUser){
          ResponseEntity<UserDto> userResponse = getUserByID(idUser);
          if( userResponse.getStatusCode() == HttpStatus.OK){
               user.setId(idUser);
               UserDto existingUser = userService.updateUser(user);
               return new ResponseEntity<>(existingUser,HttpStatus.OK);

          }else
               return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }




     @DeleteMapping("deleteUser/{idUser}")

     ResponseEntity<String> deleteUser(@PathVariable Long idUser){
          userService.deleteUser(idUser);
          return new ResponseEntity<>("user is deleted ", HttpStatus.OK);
     }
}