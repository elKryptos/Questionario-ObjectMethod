package it.objectmethod.questionario2024.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class QuestionarioResponseBuilder<T> {

    private T data;
    private HttpStatusCode statusCode = HttpStatus.OK;
    private Map<String, String> headers = new HashMap<>();

    public QuestionarioResponseBuilder() {
    }

    public QuestionarioResponseBuilder<T> data(final T data) {
        this.data = data;
        return this;
    }

    public QuestionarioResponseBuilder<T> statusCode(final HttpStatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public QuestionarioResponseBuilder<T> statusCode(final Map<String, String> headers) {
        Map<String, String> headersModified = new HashMap<>();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            headersModified.put("questionario2024_" + entry.getKey(), entry.getValue());
        }
        this.headers = headersModified;
        return this;
    }

    public ResponseEntity<T> build() {
        return new ResponseEntity<>(data, MultiValueMap.fromSingleValue(headers), statusCode);
    }
}
