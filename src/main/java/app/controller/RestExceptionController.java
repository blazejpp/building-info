package app.controller;

import app.controller.Controller;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class RestExceptionController extends Controller {

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity validationError(ConstraintViolationException exc) {
        log.debug("validation failed", exc);
        return respond(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value = NOT_FOUND)
    @ResponseBody
    public ResponseEntity notFoundError(EmptyResultDataAccessException exc) {
        log.debug("resounce not found", exc);
        return respond(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity genericError(Exception exc) {
        log.error("internal server error", exc);
        return respond(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}