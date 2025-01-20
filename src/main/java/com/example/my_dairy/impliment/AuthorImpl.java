package com.example.my_dairy.impliment;

import com.example.my_dairy.dto.UserDto;
import com.example.my_dairy.entities.User;
import com.example.my_dairy.repository.UserRepo;
import com.example.my_dairy.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorImpl implements AuthorService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final ModelMapper modelMapper;

    public AuthorImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto signup(UserDto userDto) {
        Optional<User> existingUser = userRepo.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = modelMapper.map(userDto, User.class);
        user = userRepo.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto signin(String email, String password) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        return null;
    }
}
