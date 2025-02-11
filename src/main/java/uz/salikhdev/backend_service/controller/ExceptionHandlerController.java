package uz.salikhdev.backend_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.salikhdev.backend_service.dto.MessageDto;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<MessageDto> handleException(Exception e) {
        return new ResponseEntity<>(new MessageDto(e.getMessage(), false), HttpStatus.BAD_REQUEST);
    }

}
