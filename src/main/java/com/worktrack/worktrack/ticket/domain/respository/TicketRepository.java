package com.worktrack.worktrack.ticket.domain.respository;

import com.worktrack.worktrack.ticket.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findAllByActiveTrue();
    Optional<Ticket> findById(Long aLong);
}
