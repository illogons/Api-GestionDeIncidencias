package com.worktrack.worktrack.comment.service;

import com.worktrack.worktrack.comment.Infrastructure.assembler.CommentAssembler;
import com.worktrack.worktrack.comment.domain.model.Comment;
import com.worktrack.worktrack.comment.domain.respository.CommentRepository;
import com.worktrack.worktrack.comment.dto.CommentRequestDto;
import com.worktrack.worktrack.comment.dto.CommentResponseDto;
import com.worktrack.worktrack.shared.WorkTrackException;
import com.worktrack.worktrack.ticket.Infrastructure.assembler.TicketAssembler;
import com.worktrack.worktrack.ticket.domain.model.Ticket;
import com.worktrack.worktrack.ticket.domain.respository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceCommentImpl implements ServiceComment {


    private final CommentRepository commentRepository;
    private final CommentAssembler commentAssembler;
    private final TicketAssembler ticketAssembler;
    private final TicketRepository ticketRepository;


    @Override
    @Transactional
    public List<CommentResponseDto> getComments() {
        log.info("getComments");

        List<CommentResponseDto> lista= commentRepository.findAll()
                .stream()
                .map(commentAssembler::toDto)
                .collect(Collectors.toList());

        return lista;

    }

    @Override
    public CommentResponseDto getCommentById(Long id) {
        log.info("getCommentById");

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new WorkTrackException("Comment not found with ID: " , HttpStatus.NOT_FOUND));

        log.info("Got comment successfully");
        return commentAssembler.toDto(comment);
    }

    @Override
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto dto) {
        log.info("createComment");

        Ticket ticket = ticketRepository.findById(dto.getTicketId())
                .orElseThrow(() -> new WorkTrackException("Ticket not found with ID: " + dto.getTicketId(), HttpStatus.NOT_FOUND));

        Comment comment = commentAssembler.toModel(dto);
        comment.setTicketId(ticket);
        log.info("Got comment successfully");
        return commentAssembler.toDto(commentRepository.save(comment));

    }

    @Override
    public CommentResponseDto updateComment(Long Id, CommentRequestDto dto) {
        log.info("updateComment");

        Comment a= commentRepository.findById(Id)
                .orElseThrow(() -> new WorkTrackException("Comment not found with ID: " + dto.getTicketId(), HttpStatus.NOT_FOUND));

        commentAssembler.toUpdate(dto, a);
        Comment comment = commentRepository.save(a);

        log.info("Updated comment successfully");
        return commentAssembler.toDto(comment);
    }

    @Override
    public void deleteComment(Long id ) {
        log.info("deleteComment");

        Comment com= commentRepository.findById(id)
                .orElseThrow(() -> new WorkTrackException("comment not found with ID: " , HttpStatus.NOT_FOUND));


        com.setActive(false);
        commentRepository.save(com);
        log.info("Comment successfully deleted");
    }
}
