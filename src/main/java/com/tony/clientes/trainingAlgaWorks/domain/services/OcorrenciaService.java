package com.tony.clientes.trainingAlgaWorks.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony.clientes.trainingAlgaWorks._exceptionHandle.BusinessException;
import com.tony.clientes.trainingAlgaWorks.domain.model.Entrega;
import com.tony.clientes.trainingAlgaWorks.domain.model.Ocorrencia;
import com.tony.clientes.trainingAlgaWorks.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OcorrenciaService {

    EntregaRepository entregaRepository;

    private Entrega hasEntregaById(long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new BusinessException("the Ocorrencia from  Entrega Id doesnt in DB"));
        return entrega;
    }
    /* criamos uma metodo que, registrará uma ocorrencia */
    @Transactional
    public Ocorrencia registrarOcorrenciaDeEntrega(long entregaId, String descricao) { /*
                                                                                        * 1º temos que saber se existe
                                                                                        * ou não uma de entrega.
                                                                                        * 2º Existindo , adcionaremos
                                                                                        * uma correncia.,
                                                                                        * 3º este Metodo deve adcionar
                                                                                        * um Objeto em nosso Entrega, lá
                                                                                        * naquela Lista que criamos na
                                                                                        * Entidade Entrega.
                                                                                        */
        Entrega entrega = hasEntregaById(entregaId);

                return   entrega.AdcionarOcorrenciaDescricao(descricao);

        
    }


}
