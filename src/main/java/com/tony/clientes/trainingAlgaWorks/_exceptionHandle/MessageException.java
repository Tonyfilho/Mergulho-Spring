package com.tony.clientes.trainingAlgaWorks._exceptionHandle;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL) //Fz com caso haja campos Nulos passado no Construtor no momento da Instacia, ele não seja MOSTRADOS
@Getter
@Setter
@AllArgsConstructor
public class MessageException {
    
    private Integer status; // para retornar o numero do Status
    private LocalDateTime dateTime;
    private String titulo; // para retornar a descrição  de erro
    private List<Field> field;
    
    
    @Getter
    @AllArgsConstructor
    public static class  Field  {

        private String fieldName;
        private String fieldMessage;
    }
}
