package com.tony.clientes.trainingAlgaWorks.api.modelDTO.inPutData;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInputModelDTO { /*
                                     * Nesta Classe Será o DTO de entrada de dados, ao invez de passaremos a nossas
                                     * ENTIDADES como paramentro de entrada, passaremos nosso ModelDTO, e com isto
                                     * teremos mais segurança no sistema.
                                     * Ou seja um Objeto com ID
                                     * 
                                     */
    @Valid
    @NotNull
    private ClienteIdInput clienteId; /*
                                       * 1º no cliente, só precisamo do ID, pois é um referencia e não um cadastro de
                                       * um cliente, pois o cliente já foi cadastrado.
                                       */
    @Valid
    @NotNull
    private DestinatarioInput destinatario; /* 2º um Objeto do Destinatário */
    
    @NotNull
    private  BigDecimal taxa;
    
    

}
