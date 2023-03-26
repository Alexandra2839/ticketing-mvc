package com.learn.converter;

import com.learn.dto.RoleDTO;
import com.learn.service.impl.RoleServiceImpl;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDtoConverter implements Converter<String, RoleDTO> {

    private final RoleServiceImpl roleService;

    public RoleDtoConverter(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {

        if (source == null || source.equals("")){
            return null;
        }
        return roleService.findById(Long.parseLong(source));
    }
}
