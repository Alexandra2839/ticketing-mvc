package com.learn.service;

import com.learn.dto.RoleDTO;
import com.learn.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO role);
    UserDTO findById(String username);
    List<UserDTO> findAll();
    void deleteById(String username);
}
