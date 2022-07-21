package com.tony.clientes.trainingAlgaWorks._exceptionHandle;

import java.io.ObjectInputStream.GetField;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice /** Esta anotação diz para spring q esta classe é a que controla as exception */
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        // return super.handleMethodArgumentNotValid(ex, headers, status, request);
        List<MessageException.Field> field = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldErrorName = ((FieldError) error).getField(); // buscando o nome do campo de erro,tem q fz um
                                                                     // cast
            String fieldErrorMessage = error.getDefaultMessage(); // buscando a messagem, não precisa fz um cast pois já
                                                                  // vem direto

            field.add(new MessageException.Field(fieldErrorName, fieldErrorMessage));
        }

        MessageException messageException = new MessageException(status.value(), LocalDateTime.now(),
                "One or more The field are empty, one or more fields  are Mandatory", field);
        return handleExceptionInternal(ex, messageException, headers, status, request);
    }

}// end class
