package com.worktrack.worktrack.ticket.dto;

import com.worktrack.worktrack.ticket.domain.enums.TicketPriority;
import com.worktrack.worktrack.ticket.domain.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDto {


    @NotBlank(message = "the issue have to have a title")
    private String title;

    @Size(max = 5000 , message = "5000 max")
    private String description;

    @NotNull
    private TicketStatus ticketStatus;
    @NotNull
    private TicketPriority ticketPriority;

    @NotNull( message = "project is required")
    private Long projectId;






}
