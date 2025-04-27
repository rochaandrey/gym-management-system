package com.manager.workout.workout.exceptions;

import com.manager.workout.workout.dto.general.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDto> handleNullPointerException (){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                "Nullpointer exception",
                "Verifique a integridade dos dados enviados",
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                "IllegalArgumentException",
                "Verifique a integridade dos dados enviados",
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
}
