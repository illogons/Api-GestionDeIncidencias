package com.worktrack.worktrack.comment.dto;

import com.worktrack.worktrack.ticket.domain.model.Ticket;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {


    @NotBlank(message = "message required")
    private String message;

    private Long ticketId;


}
