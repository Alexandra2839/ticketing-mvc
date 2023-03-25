package com.learn.service;

import com.learn.dto.ProjectDTO;

public interface ProjectService extends CrudService<ProjectDTO, String> {

    void complete(ProjectDTO project);

}
