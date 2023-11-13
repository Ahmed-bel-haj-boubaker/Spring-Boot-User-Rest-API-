package com.springboot.springbootresttfulwebservice.mapper;

import com.springboot.springbootresttfulwebservice.dto.UserDto;
import com.springboot.springbootresttfulwebservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
