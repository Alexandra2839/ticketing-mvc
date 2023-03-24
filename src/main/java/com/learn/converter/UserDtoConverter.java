package com.learn.converter;

import com.learn.dto.UserDTO;
import com.learn.service.impl.UserServiceImpl;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationPropertiesBinding
public class UserDtoConverter implements Converter<String, UserDTO> {

    private final UserServiceImpl userService;

    public UserDtoConverter(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public UserDTO convert(String source) {
        return userService.findById(source);
    }
}
