package by.shylau.currenciesexchange.exception;

import by.shylau.currenciesexchange.exception.BadRequestException;
import by.shylau.currenciesexchange.exception.ConflictException;
import by.shylau.currenciesexchange.exception.InternalServerException;
import by.shylau.currenciesexchange.exception.NotFoundException;
import by.shylau.currenciesexchange.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    //400
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorMessage> handleBadRequestException(RuntimeException ex) {

        return new ResponseEntity<>(ErrorMessage.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    //404
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorMessage> handleNotFoundException(RuntimeException ex) {

        return new ResponseEntity<>(ErrorMessage.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    //409
    @ExceptionHandler(ConflictException.class)
    protected ResponseEntity<ErrorMessage> handleConflictException(RuntimeException ex) {

        return new ResponseEntity<>(ErrorMessage.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .build(),
                HttpStatus.CONFLICT);
    }

    //500
    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<ErrorMessage> handleProblemWithDBException(RuntimeException ex) {

        return new ResponseEntity<>(ErrorMessage.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}