package com.worktrack.worktrack.user.Infrastructure.assembler;

import com.worktrack.worktrack.user.domain.model.User;
import com.worktrack.worktrack.user.dto.UserRequestDto;
import com.worktrack.worktrack.user.dto.UserResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public User toModel (UserRequestDto dto){
        User  user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
    public void toUpdate( UserRequestDto dto, User user){
        BeanUtils.copyProperties(dto, user);
    }
    public UserResponseDto toDto(User model){

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(model, userResponseDto);

        if(model.getDepartmentId() != null){
            userResponseDto.setDepartmentId(model.getDepartmentId().getId());
        }

        return userResponseDto;
    }

}
