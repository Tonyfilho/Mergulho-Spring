package com.tony.clientes.trainingAlgaWorks.domain.services;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony.clientes.trainingAlgaWorks._exceptionHandle.BusinessException;
import com.tony.clientes.trainingAlgaWorks.domain.model.Entrega;
import com.tony.clientes.trainingAlgaWorks.domain.model.StatusEntrega;
import com.tony.clientes.trainingAlgaWorks.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoDeEntregaService {
    EntregaRepository entregaRepository;
    EntregaService entregaService;

    @Transactional
    public void finalizaEntrega(Long entregaId) { /**
                                                   * Este metodo não retorna Nada, só mudará o status da entrega
                                                   * 1º temos q ver se temos ou não um entrega na DB.
                                                   * 2º Optei em Não por REGRAS de Negocio na Entidade, por isto foi
                                                   * criado o Metodo Finaliza.
                                                   */
        Entrega entrega = entregaService.getUmaEntrega(entregaId);
        entregaRepository.save(this.finaliza(entrega));
    }

    private Entrega finaliza(Entrega entregaFinalizada) {
        Entrega entrega = new Entrega();
        if (!entrega.getStatusEntrega().equals(entregaFinalizada.getStatusEntrega())) {
            throw new BusinessException("Delivery can not be done !");
        }
        entrega.setStatusEntrega(StatusEntrega.FINALIZADA);
        entrega.setDatafinalizacao(OffsetDateTime.now());
        return entrega;
    }
}
