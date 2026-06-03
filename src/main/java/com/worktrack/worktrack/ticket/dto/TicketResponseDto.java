package com.worktrack.worktrack.ticket.dto;


import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.ticket.domain.enums.TicketPriority;
import com.worktrack.worktrack.ticket.domain.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    private Long id;
    private String title;
    private String description;
    private TicketStatus ticketStatus;
    private TicketPriority ticketPriority;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long projectId;







}
