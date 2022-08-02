package com.tony.clientes.trainingAlgaWorks.api.modelDTO.inPutData;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInputModelDTO {/*
                              * Nesta Classe Será o DTO de entrada de dados, ao invez de passaremos a nossas
                              * ENTIDADES como paramentro de entrada, passaremos nosso ModelDTO, e com isto
                              * teremos mais segurança no sistema.
                              * Ou seja um Objeto com ID
                              * 
                              */

    @NotNull
    private Long id;
}
