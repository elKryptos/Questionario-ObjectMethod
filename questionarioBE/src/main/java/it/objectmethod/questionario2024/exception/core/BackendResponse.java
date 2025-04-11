package it.objectmethod.questionario2024.exception.core;

import it.objectmethod.questionario2024.exception.NotFoundException;
import it.objectmethod.questionario2024.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class BackendResponse {

    @ExceptionHandler(NotFoundException.class)
    public ApiResponse<ErrorDetails> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND
        );
        return ApiResponse.<ErrorDetails>builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .data(errorDetails)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleJakartaViolation(final ConstraintViolationException e, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<ApiResponse<ErrorDetails>> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                e.getMessage(),
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST
//        );
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(new ApiResponse<>(Constants.BAD_REQUEST, errorDetails));
//
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiResponse<ErrorDetails>> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
//        String errorMessage = Constants.ERROR_MESSAGE;
//
//        Map<String, String> fieldErrors = e.getBindingResult().getFieldErrors().stream()
//                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                errorMessage,
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST,
//                fieldErrors
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new ApiResponse<>(Constants.BAD_REQUEST, errorDetails));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<ErrorDetails>> handleException(Exception e, HttpServletRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                e.getMessage(),
//                request.getRequestURI(),
//                HttpStatus.INTERNAL_SERVER_ERROR
//        );
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new ApiResponse<>(Constants.INTERNAL_SERVER_ERROR, errorDetails));
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<ApiResponse<ErrorDetails>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                e.getMessage(),
//                request.getRequestURI(),
//                HttpStatus.METHOD_NOT_ALLOWED
//        );
//        return ResponseEntity
//                .status(HttpStatus.METHOD_NOT_ALLOWED)
//                .body(new ApiResponse<>(Constants.METHOD_NOT_ALLOWED, errorDetails));
//    }
}
