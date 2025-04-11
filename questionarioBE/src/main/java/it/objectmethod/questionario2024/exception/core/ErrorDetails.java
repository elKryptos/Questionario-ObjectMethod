package it.objectmethod.questionario2024.exception.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private HttpStatus status;
    private Map<String, String> errors;

    public ErrorDetails(LocalDateTime timestamp, String message, String details, HttpStatus status) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }
}
