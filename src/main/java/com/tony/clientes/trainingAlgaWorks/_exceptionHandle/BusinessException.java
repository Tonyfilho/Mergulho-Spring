package com.tony.clientes.trainingAlgaWorks._exceptionHandle;


/**Temos q extender RunTime Exceptions */
// @AllArgsConstructor
public class BusinessException extends RuntimeException {
     private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

     

}
