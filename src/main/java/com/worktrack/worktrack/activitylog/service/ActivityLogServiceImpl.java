package com.worktrack.worktrack.activitylog.service;

import com.worktrack.worktrack.activitylog.Infrastructure.assembler.ActivityLogAssembler;
import com.worktrack.worktrack.activitylog.domain.respository.ActivityLogRepository;
import com.worktrack.worktrack.activitylog.domain.model.ActivityLog;
import com.worktrack.worktrack.activitylog.dto.ActivityLogRequestDto;
import com.worktrack.worktrack.activitylog.dto.ActivityLogResponseDto;
import com.worktrack.worktrack.user.domain.model.User;
import com.worktrack.worktrack.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final ActivityLogAssembler activityLogAssembler;
    private final UserRepository userRepository;

    @Override
    public List<ActivityLogResponseDto> getLogsByUser(Long userId) {
        log.info("Getting activity logs for user: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        return activityLogRepository.findByUser(user)
                .stream()
                .map(activityLogAssembler::toDto)
                .toList();
    }

    @Override
    public ActivityLogResponseDto registerLog(ActivityLogRequestDto dto) {
        log.info("Registering activity log: {}", dto);

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + dto.getUserId()));

        ActivityLog model = activityLogAssembler.toModel(dto);
        model.setUserId(user);
        ActivityLog saved = activityLogRepository.save(model);
        log.info("Registered activity log successfully");
        return activityLogAssembler.toDto(saved);
    }

    @Override
    public List<ActivityLogResponseDto> getAllLogs() {
        log.info("Getting all activity logs");
        return activityLogRepository.findAll()
                .stream()
                .map(activityLogAssembler::toDto)
                .toList();
    }
}