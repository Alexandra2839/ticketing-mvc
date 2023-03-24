package com.learn.service;

import com.learn.dto.UserDTO;

import java.util.List;

public interface CrudService <T, ID>{

    T save(T role);
    T findById(ID username);
    List<T> findAll();
    void deleteById(ID username);
    void update(T object);

}
