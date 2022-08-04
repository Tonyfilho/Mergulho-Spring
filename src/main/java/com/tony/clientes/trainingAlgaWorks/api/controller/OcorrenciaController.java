package com.tony.clientes.trainingAlgaWorks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tony.clientes.trainingAlgaWorks.api.modelDTO.inPutData.OcorrenciaInputModelDTO;
import com.tony.clientes.trainingAlgaWorks.api.modelDTO.outPutData.OcorrenciaOutPutModelDTO;
import com.tony.clientes.trainingAlgaWorks.domain.model.Entrega;
import com.tony.clientes.trainingAlgaWorks.domain.model.Ocorrencia;
import com.tony.clientes.trainingAlgaWorks.domain.services.EntregaService;
import com.tony.clientes.trainingAlgaWorks.domain.services.OcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entrega/{entregaId}/ocorrencias") /*
                                                     * Por ser um SubRecurso,t emos um PathVariable sendo passando no
                                                     * requestMapping
                                                     */
public class OcorrenciaController {
    private EntregaService entregaService;
    private OcorrenciaService ocorrenciaService;
    /*
     * Não usamos o JPA, mas setamos diretamente na Entidade Entrega, por isto q
     * estamos usando service, com metodo que vai na entidade
     */

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OcorrenciaOutPutModelDTO registrar(@PathVariable Long entregaId,
            @Valid @RequestBody OcorrenciaInputModelDTO entregaDescricao) {
        OcorrenciaOutPutModelDTO ocorrenciaOutPutModelDTO;
        Ocorrencia ocorrenciaRegistrada = ocorrenciaService.registrarOcorrenciaDeEntrega(entregaId,
                entregaDescricao.getDescricao());
        ocorrenciaOutPutModelDTO = ocorrenciaService.entityToModelDTO(ocorrenciaRegistrada);
        return ocorrenciaOutPutModelDTO;
    }

    /**
     * @param entregaId
     * @return
     */
    @GetMapping
    public List<OcorrenciaOutPutModelDTO> listarOcorrencias(@PathVariable Long entregaId) { /*
                                                                                             * Para termos uma lista de
                                                                                             * Ocorrencias, precisamos
                                                                                             * 1º Veremos se existe uma
                                                                                             * Entrega, então buscaremos
                                                                                             * na DB a entrega, caso não
                                                                                             * exista o Serviço de
                                                                                             * entrega vai lançar um
                                                                                             * error.
                                                                                             * 2º como Não criamos um
                                                                                             * repositorio para
                                                                                             * Ocorrencia e estamos setando  na propria ENTITY,usaremos o 
                                                                                             * Get para ter acesso ao OBJETO Ocorrencia
                                                                                             * e sua LISTA, é o que chamamos de Carregamento LAZY LOADING */
                                                                                         
                                                                                            

        Entrega entregaExistente = entregaService.getUmaEntrega(entregaId);
        List<Ocorrencia> listaDeOcorrencia = entregaExistente.getOcorrencias();
       List<OcorrenciaOutPutModelDTO> ocorrenciaModelDTO = ocorrenciaService.listEntityToListModelDTO(listaDeOcorrencia);
        return ocorrenciaModelDTO;

    }

}
