package com.springboot.springbootresttfulwebservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserDto {

     Long id;
     @NotEmpty(message = "User firstname should not be null or empty")
     String firstName;

     @NotEmpty(message = "User lastName should not be null or empty")
     String lastName;

     @NotEmpty(message = "User email should not be null or empty")
     @Email(message ="email address should be valid" )
     String email;


}
