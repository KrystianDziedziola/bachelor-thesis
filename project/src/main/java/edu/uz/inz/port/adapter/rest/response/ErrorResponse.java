package edu.uz.inz.port.adapter.rest.response;

import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String title;
    private final int status;
    private final String detail;

    public static ResponseEntity prepare(
        final String title, final String exceptionMessage, final HttpStatus status) {
        final HttpHeaders headers = new HttpHeaders();
        headers
            .setContentType(new MediaType("application", "problem+json", StandardCharsets.UTF_8));
        final ErrorResponse errorResponse = new ErrorResponse(title, status.value(),
            exceptionMessage);
        return new ResponseEntity<>(errorResponse, headers, status);
    }
}
