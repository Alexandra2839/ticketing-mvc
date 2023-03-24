package com.learn.service.impl;

import com.learn.dto.ProjectDTO;
import com.learn.enums.Status;
import com.learn.service.ProjectService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    @Override
    public ProjectDTO save(ProjectDTO project) {
        if (project.getProjectStatus()==null){
            project.setProjectStatus(Status.OPEN);
        }
        return super.save(project.getProjectCode(), project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return null;
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO project) {
        super.update(project.getProjectCode(),project);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

}
