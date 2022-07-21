package com.tony.clientes.trainingAlgaWorks._exceptionHandle;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
