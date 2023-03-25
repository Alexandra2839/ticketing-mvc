package com.learn.service;

import com.learn.dto.TaskDTO;
import com.learn.dto.UserDTO;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long>{

    List<TaskDTO> findTasksByManager(UserDTO manager);
}
