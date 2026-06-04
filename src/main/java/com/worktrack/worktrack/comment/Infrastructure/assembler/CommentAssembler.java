package com.worktrack.worktrack.comment.Infrastructure.assembler;

import com.worktrack.worktrack.comment.domain.model.Comment;
import com.worktrack.worktrack.comment.dto.CommentRequestDto;
import com.worktrack.worktrack.comment.dto.CommentResponseDto;
import com.worktrack.worktrack.user.domain.model.User;
import com.worktrack.worktrack.user.dto.UserRequestDto;
import com.worktrack.worktrack.user.dto.UserResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CommentAssembler {

    public Comment toModel (CommentRequestDto dto){
        Comment comment = new Comment();
        BeanUtils.copyProperties(dto, comment);
        return comment;
    }
    public CommentResponseDto toModel (Comment model){
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        BeanUtils.copyProperties(model, commentResponseDto);

        if(model.getTicketId() != null){
            commentResponseDto.setTicketId(model.getTicketId().getId());
        }

        return commentResponseDto;
    }

}


