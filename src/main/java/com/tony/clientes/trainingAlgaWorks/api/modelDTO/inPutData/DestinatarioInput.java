package com.tony.clientes.trainingAlgaWorks.api.modelDTO.inPutData;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class DestinatarioInput { /* Serão no mesmo Atributos da Classe Entrega */
    @NotBlank
    private String nome;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String bairro;

}
