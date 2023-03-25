package com.learn.converter;

import com.learn.dto.ProjectDTO;
import com.learn.service.ProjectService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ProjectDtoConverter implements Converter<String, ProjectDTO> {

  private final ProjectService projectService;

    public ProjectDtoConverter(ProjectService projectService) {
        this.projectService = projectService;
    }


    @Override
    public ProjectDTO convert(String source) {
        return projectService.findById(source);
    }
}
