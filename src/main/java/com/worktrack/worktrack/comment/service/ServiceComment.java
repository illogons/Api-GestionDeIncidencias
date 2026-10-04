package com.worktrack.worktrack.comment.service;


import com.worktrack.worktrack.comment.dto.CommentRequestDto;
import com.worktrack.worktrack.comment.dto.CommentResponseDto;

import java.util.List;

public interface ServiceComment {

    void deleteComment(Long id);
    CommentResponseDto getCommentById(Long id);
    List<CommentResponseDto> getComments();
    CommentResponseDto createComment(CommentRequestDto dto);
    CommentResponseDto updateComment(Long Id,CommentRequestDto dto);

}
