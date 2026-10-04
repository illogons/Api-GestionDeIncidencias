package com.worktrack.worktrack.shared;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class WorkTrackException extends RuntimeException {

    private final HttpStatus status;

    public WorkTrackException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}