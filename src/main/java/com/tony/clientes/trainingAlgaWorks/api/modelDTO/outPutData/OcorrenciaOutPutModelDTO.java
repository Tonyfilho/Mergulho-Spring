package com.tony.clientes.trainingAlgaWorks.api.modelDTO.outPutData;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaOutPutModelDTO { /*
                                         * Aqui neste DTO de Output teremos somente o que interessa, o Id a Descrição e
                                         * a DataRegistro
                                         */

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;

}
