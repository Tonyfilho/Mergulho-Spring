package com.tony.clientes.trainingAlgaWorks.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tony.clientes.trainingAlgaWorks.model.Entrega;
import com.tony.clientes.trainingAlgaWorks.services.EntregaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas")
public class EntregaController {

    EntregaService entregaService;

    /** Pedir um entrega */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /*
     * Quando o BeanValidator for fz a validação do @Valid na Entidade Entrega, por
     * padrão ele usará Default.class Ex: @NotBlank, @NotNull
     * mas quando chegar lá no relacionamento @ManyToOne com a entidade Cliente, ele
     * encontrará a anotação @Valid e @ConvertGroup(from = Default.class, to =
     * CustomClienteID.class). E esta anotação @Convert..irá falar para Não se
     * preoculpe com as outras validações da entidade Cliente, pois a propria
     * entidade cliente validará isto com o BeanValidator mas somente com a
     * Validação que CUSTOMISAMOS, que neste caso é a @NotNull(groups =
     * ValidationGroups.CustomClienteID.class)
     */
    public Entrega solicitarEntrega(@Valid @RequestBody Entrega entrega) {
        return entregaService.getEntrega(entrega);
    }

}
