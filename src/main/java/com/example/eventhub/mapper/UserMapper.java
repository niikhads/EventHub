package com.example.eventhub.mapper;

import com.example.eventhub.dto.request.RegisterRequest;
import com.example.eventhub.dto.response.UserDto;
import com.example.eventhub.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",imports={LocalDateTime.class})
public interface UserMapper {
    User toUser(RegisterRequest registerRequest);
    UserDto toUserDto(User savedUser);
}