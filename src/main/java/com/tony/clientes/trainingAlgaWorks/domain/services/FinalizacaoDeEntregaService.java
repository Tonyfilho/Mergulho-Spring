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
    
        private Entrega finaliza(Entrega entregaFinalizada) {       
            if (!entregaFinalizada.getStatusEntrega().equals(StatusEntrega.PENDENTE)) {
                throw new BusinessException("Delivery can not be done !");
            }
            entregaFinalizada.setStatusEntrega(StatusEntrega.FINALIZADA);
            entregaFinalizada.setDatafinalizacao(OffsetDateTime.now());
            return entregaFinalizada;
        }
    
        private Entrega cancela(Entrega entregaFinalizada) {       
            if (entregaFinalizada.getStatusEntrega().equals(StatusEntrega.FINALIZADA)) {
                throw new BusinessException("The Delivery was done, and can not be cancel !");
            }
            entregaFinalizada.setStatusEntrega(StatusEntrega.CANCELADA);
            entregaFinalizada.setDatafinalizacao(OffsetDateTime.now());
            return entregaFinalizada;
        }

    @Transactional
    public void finalizaEntrega(Long entregaId) { /**
                                                   * Este metodo não retorna Nada, só mudará o status da entrega
                                                   * 1º temos q ver se temos ou não um entrega na DB.
                                                   * 2º Optei em Não por REGRAS de Negocio na Entidade, por isto foi
                                                   * criado o Metodo Finaliza.
                                                   */
        Entrega entregaConfirmada = entregaService.getUmaEntrega(entregaId);
        Entrega entrega = this.finaliza(entregaConfirmada);
        entregaRepository.save(entrega);
    }
    @Transactional
    public void cancelaEntrega(Long entregaId) { /**
                                                   * Este metodo não retorna Nada, só mudará o status da entrega
                                                   * 1º temos q ver se temos ou não um entrega na DB.
                                                   * 2º Optei em Não por REGRAS de Negocio na Entidade, por isto foi
                                                   * criado o Metodo Finaliza.
                                                   */
        Entrega entregaConfirmada = entregaService.getUmaEntrega(entregaId);
        Entrega entrega = this.cancela(entregaConfirmada);
        entregaRepository.save(entrega);
    }
}
