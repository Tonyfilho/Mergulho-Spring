package com.tony.clientes.trainingAlgaWorks.api.modelDTO.inPutData;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInputModelDTO { /*
                                        * Como aqui, é um Input so precisamos enviar
                                        * lá no PostMan uma descrição
                                        */

    @NotBlank
    private String descricao;

}
