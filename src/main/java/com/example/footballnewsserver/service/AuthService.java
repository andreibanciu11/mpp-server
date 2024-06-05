package com.example.footballnewsserver.service;

import com.example.footballnewsserver.dto.SignupDTO;
import com.example.footballnewsserver.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
