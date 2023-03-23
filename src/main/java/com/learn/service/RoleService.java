package com.learn.service;

import com.learn.dto.RoleDTO;
import com.learn.dto.UserDTO;

import java.util.List;

public interface RoleService {

    RoleDTO save(RoleDTO role);
    RoleDTO findById(Long id);
    List<RoleDTO> findAll();
    void deleteById(Long id);




}
