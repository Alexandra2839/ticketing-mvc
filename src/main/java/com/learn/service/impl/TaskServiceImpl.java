package com.learn.service.impl;

import com.learn.dto.RoleDTO;
import com.learn.dto.TaskDTO;
import com.learn.service.RoleService;
import com.learn.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO task) {
        return super.save(task.getId(), task);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO task) {
        super.update(task.getId(), task);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
