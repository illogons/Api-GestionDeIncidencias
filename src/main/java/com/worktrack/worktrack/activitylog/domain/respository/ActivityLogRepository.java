package com.worktrack.worktrack.activitylog.domain.respository;

import com.worktrack.worktrack.activitylog.domain.model.ActivityLog;
import com.worktrack.worktrack.ticket.domain.model.Ticket;
import com.worktrack.worktrack.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long> {

    List<ActivityLog> findAll();
    Optional<ActivityLog> findById(Long aLong);
    List<ActivityLog> findByUser(User user);

}
