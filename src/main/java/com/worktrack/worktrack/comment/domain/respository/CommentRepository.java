package com.worktrack.worktrack.comment.domain.respository;

import com.worktrack.worktrack.comment.domain.model.Comment;
import com.worktrack.worktrack.ticket.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Comment> findByTicket(Ticket ticket);
}
