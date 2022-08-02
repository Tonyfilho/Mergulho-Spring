package com.tony.clientes.trainingAlgaWorks.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entrega/{entregaId}/ocorrencias") /*
                 * Por ser um SubRecurso,t emos um PathVariable sendo passando no requestMapping
                 */
public class OcorrenciaController {

    

}
