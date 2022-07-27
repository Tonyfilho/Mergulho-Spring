package com.tony.clientes.trainingAlgaWorks.services;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.tony.clientes.trainingAlgaWorks._exceptionHandle.BusinessException;
import com.tony.clientes.trainingAlgaWorks.model.Cliente;
import com.tony.clientes.trainingAlgaWorks.model.Entrega;
import com.tony.clientes.trainingAlgaWorks.model.StatusEntrega;
import com.tony.clientes.trainingAlgaWorks.repository.ClienteRepository;
import com.tony.clientes.trainingAlgaWorks.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {

    private EntregaRepository entregaRepository;
    private ClienteRepository clienteRepository;


    
    private Cliente hasCliente(Entrega entrega) {
        /**
         * fazendo uma busca e uma validação e lancando um erro caso o ID da class Cliente Não
         * existir o findByid é OPTIONAL ele fornece o metodo ORELSETHROW q for passado lança um Erro
         * caso exista a var local cliente recebe o ID.
         */
        Cliente cliente = clienteRepository.findById(entrega.getCliente().getId())
        .orElseThrow(() -> new BusinessException("Client Id doesnt in DB"));
        return cliente;
    }
    private Entrega hasId(Long id) {      
        Entrega entrega = entregaRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Entrega Id doesnt in DB"));
        return  entrega;
    }

    @Transactional
    public Entrega getEntrega(Entrega entrega) {
        /**
         * É aqui neste espaço q são implementada as regras de negocio, Ex:
         * Horario de entrega.
         * Dias q podem ou não ter entregas.
         * Locais onde podem ou não ter entrega.
         * tudo isto ANTES do RETURN.
         * As Propriedade da class q são Não são definidas pelo Usuário devem ser
         * SETADAS
         * aqui Ex: Hora e entrega, Status da entrega etc
         */
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        Cliente cliente = hasCliente(entrega);
        /**
         * Caso exista tb o cliente, ja podemos RETORNA-LO de forma que aparecerá
         * as informações SETANDO A VAR ENTREGA.setCliente, de forma q ja aparecerá
         * todas as informações do cliente e não mais o retorno das propriedade vazias.
         */
         entrega.setCliente(cliente);
        return entregaRepository.save(entrega);
    }

    public Entrega getUmaEntrega(Long id) {
        Entrega entregaExiste = hasId(id);
      return  entregaExiste;
    }


}
