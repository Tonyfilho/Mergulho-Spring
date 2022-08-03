package com.tony.clientes.trainingAlgaWorks._exceptionHandle;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@ControllerAdvice /** Esta anotação diz para spring q esta classe é a que controla as exception */
@AllArgsConstructor /**criando o Construtor para  injetar o MessageSource ou usar o @Autowired */
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**Temos q injetar a Interface messageSource para que as mensagens customizada vindo do arquivo Message.Properties */
   
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        // return super.handleMethodArgumentNotValid(ex, headers, status, request);
        List<MessageException.Field> field = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldErrorName = ((FieldError) error).getField(); // buscando o nome do campo de erro,tem q fz um
                                                                     // cast
           /*  String fieldErrorMessage = error.getDefaultMessage(); // buscando a messagem, não precisa fz um cast pois já*/
            String fieldErrorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale()); // buscando a messagem, agora do MessageSource e passando o Locale do Indioma
                                                                 

            field.add(new MessageException.Field(fieldErrorName, fieldErrorMessage));
        }

        MessageException messageException = new MessageException(status.value(), OffsetDateTime.now(),
                "One or more The field are empty, one or more fields  are Mandatory", field);
        return handleExceptionInternal(ex, messageException, headers, status, request);
    }

    /**Para q a exceoption seja tratada por aqui temos que ter a anotação e passar a class como paramentro */
   @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        MessageException messageException = new MessageException(status.value(), OffsetDateTime.now(),
        ex.getMessage(), null);
        return handleExceptionInternal(ex, messageException, new HttpHeaders(), status, request);

    }
    /**Criamos esta Exception p/ retornar 404 quando Passar lá no PostMan um ID que não existe localhost:8080/entrega/200/ocorrencias */
   @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        MessageException messageException = new MessageException(status.value(), OffsetDateTime.now(),
        ex.getMessage(), null);
        return handleExceptionInternal(ex, messageException, new HttpHeaders(), status, request);

    }

}// end class
