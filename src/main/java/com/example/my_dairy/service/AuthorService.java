package com.example.my_dairy.service;

import com.example.my_dairy.dto.UserDto;

public interface AuthorService {
    UserDto signup(UserDto userDto);
    UserDto signin(String email, String password);
}
