package it.objectmethod.questionario2024.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Deprecated
@Setter
@Builder
public class ApiResponse<T> extends ResponseEntity<T> {
    @Getter
    private T data;
    private HttpStatusCode statusCode;
    private String message;

    public ApiResponse(T data, HttpStatusCode status, String message) {
        super(data, MultiValueMap.fromSingleValue(Map.of("message", message == null ? "" : message)), status);
    }

}
