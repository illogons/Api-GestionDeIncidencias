package com.worktrack.worktrack.ticket.service;

import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.project.domain.respository.ProjectRepository;
import com.worktrack.worktrack.ticket.Infrastructure.assembler.TicketAssembler;
import com.worktrack.worktrack.ticket.domain.model.Ticket;
import com.worktrack.worktrack.ticket.domain.respository.TicketRepository;
import com.worktrack.worktrack.ticket.dto.TicketRequestDto;
import com.worktrack.worktrack.ticket.dto.TicketResponseDto;
import com.worktrack.worktrack.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketAssembler ticketAssembler;
    private final ProjectRepository projectRepository;


    @Override
    @Transactional
    public List<TicketResponseDto> getTickets() {
        log.info("getTicket()");

        List<TicketResponseDto> lista = ticketRepository.findAll()
                .stream()
                .map(ticketAssembler::toDto)
                .toList();

        log.info("getTicket(): lista={}", lista);
        return lista;
    }

    @Override
    public TicketResponseDto getTicketById(Long Id) {
        log.info("getTicketById()");

        TicketResponseDto ticke =  ticketRepository.findById(Id)
                .filter(model -> Boolean.TRUE.equals(model.getActive()))
                .map(ticketAssembler::toDto).orElseThrow(()-> new NoSuchElementException("Ticket not found"));

        log.info("getTicketById(): ticke={}", ticke);
        return ticke;


    }

    @Override
    @Transactional
    public TicketResponseDto createTicket(TicketRequestDto dto) {
        log.info("createTicket()");

       Project proje = projectRepository.findById(dto.getProjectId())
               .orElseThrow( () -> new RuntimeException("Project not found with ID: " + dto.getProjectId()));

       Ticket model = ticketAssembler.toModel(dto);
       model.setProjectId(proje);
       log.info("createTicket(): model={}", model);

       return ticketAssembler.toDto(ticketRepository.save(model));



    }

    @Override
    @Transactional
    public TicketResponseDto updateTicket(Long Id, TicketRequestDto dto) {
        log.info("updateTicket()");

        Ticket ticke =  ticketRepository.findById(Id)
                .orElseThrow(()-> new NoSuchElementException("Ticket not found"));
        Project proje = projectRepository.findById(dto.getProjectId())
                .orElseThrow( () -> new RuntimeException("Project not found with ID: " + dto.getProjectId()));

        ticketAssembler.toUpdate(dto, ticke);
        ticke.setProjectId(proje);

        log.info("updateTicket(): ticke={}", ticke);
        return ticketAssembler.toDto(ticketRepository.save(ticke));

    }

    @Override
    public void deleteTicket(Long Id, TicketRequestDto user) {
        log.info("deleteTicket()");

        Ticket ticke =  ticketRepository.findById(Id)
                .filter(model -> Boolean.TRUE.equals(model.getActive()))
                .orElseThrow(()-> new NoSuchElementException("Ticket not found"));

        ticke.setActive(false);

        ticketRepository.save(ticke);

        log.info("deleteTicket(): ticke={}", ticke);

    }
}
