package com.tony.clientes.trainingAlgaWorks._exceptionHandle;

public class EntityNotFoundException extends BusinessException {/*
                                                                 * Vamos q extender Business Exception, por ser uma
                                                                 * Exception mas precisa,
                                                                 * quando não temos uma entidade e queremos retornar o
                                                                 * 404
                                                                 */

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);

    }

}
