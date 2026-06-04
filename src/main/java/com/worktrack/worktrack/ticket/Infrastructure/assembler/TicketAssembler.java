package com.worktrack.worktrack.ticket.Infrastructure.assembler;

import com.worktrack.worktrack.ticket.domain.model.Ticket;
import com.worktrack.worktrack.ticket.dto.TicketRequestDto;
import com.worktrack.worktrack.ticket.dto.TicketResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TicketAssembler {

    public Ticket toModel (TicketRequestDto dto){
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(dto, ticket);
        return ticket;
    }
    public void toUpdate (TicketRequestDto dto, Ticket ticket){
        BeanUtils.copyProperties(dto, ticket);
    }
    public TicketResponseDto toDto(Ticket model){
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        BeanUtils.copyProperties(model, ticketResponseDto);

        if(model.getProjectId() != null){
            ticketResponseDto.setProjectId(model.getProjectId().getId());
        }

        return ticketResponseDto;

    }
}
