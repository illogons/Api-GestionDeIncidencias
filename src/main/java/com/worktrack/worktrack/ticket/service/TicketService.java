package com.worktrack.worktrack.ticket.service;

import com.worktrack.worktrack.ticket.dto.TicketRequestDto;
import com.worktrack.worktrack.ticket.dto.TicketResponseDto;

import java.util.List;

public interface TicketService {

    List<TicketResponseDto> getTickets();
    TicketResponseDto getTicketById(Long Id);
    TicketResponseDto createTicket(TicketRequestDto user);
    TicketResponseDto updateTicket(Long Id, TicketRequestDto user);
    void deleteTicket(Long Id, TicketRequestDto user);

}
