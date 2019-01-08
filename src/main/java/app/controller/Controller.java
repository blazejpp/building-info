package app.controller;

import app.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Slf4j
public abstract class Controller {

    protected <T> ResponseEntity respond(T body, HttpStatus status) throws IOException {
        log.info("responding with status {} - {}, body: {}", status.value(), status.getReasonPhrase(), JsonUtils.toJson(body));
        return ResponseEntity.status(status).body(body);
    }

    protected ResponseEntity respond(HttpStatus status) {
        log.info("responding with status {} - {}", status.value(), status.getReasonPhrase());
        return ResponseEntity.status(status).body(String.format("%d - %s", status.value(), status.getReasonPhrase()));
    }
}
