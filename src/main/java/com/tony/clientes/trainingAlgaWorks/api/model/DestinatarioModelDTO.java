package com.tony.clientes.trainingAlgaWorks.api.model;
/*
 * Esta Classe é o DTO da classe Entrega, usamos este padrão por segurança Não 
  expor a nossa ENTIDADE
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioModelDTO {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

}
