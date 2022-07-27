package com.tony.clientes.trainingAlgaWorks.domain.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tony.clientes.trainingAlgaWorks._exceptionHandle.BusinessException;
import com.tony.clientes.trainingAlgaWorks.api.model.EntregaModelDTO;
import com.tony.clientes.trainingAlgaWorks.domain.model.Cliente;
import com.tony.clientes.trainingAlgaWorks.domain.model.Entrega;
import com.tony.clientes.trainingAlgaWorks.domain.model.StatusEntrega;
import com.tony.clientes.trainingAlgaWorks.domain.repository.ClienteRepository;
import com.tony.clientes.trainingAlgaWorks.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {

    private EntregaRepository entregaRepository;
    private ClienteRepository clienteRepository;

    ModelMapper modelMapper;/*
                             * OBS: por não ser Uma biblioteca SPRING o MapperModel, precisa ter um class
                             * de @Bean
                             * para mostrar ao Spring q precisa ser injetada em nosso controller.
                             * ****** olha o error do console: Action:
                             * Consider defining a bean of type 'org.modelmapper.ModelMapper' in your
                             * configuration.
                             */

    private Cliente hasCliente(Entrega entrega) {
        /**
         * fazendo uma busca e uma validação e lancando um erro caso o ID da class
         * Cliente Não
         * existir o findByid é OPTIONAL ele fornece o metodo ORELSETHROW q for passado
         * lança um Erro
         * caso exista a var local cliente recebe o ID.
         */
        Cliente cliente = clienteRepository.findById(entrega.getCliente().getId())
                .orElseThrow(() -> new BusinessException("Client Id doesnt in DB"));
        return cliente;
    }

    private Entrega hasId(Long id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Entrega Id doesnt in DB"));
        return entrega;
    }

    @Transactional
    public EntregaModelDTO getEntrega(Entrega entrega) {
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
        entregaRepository.save(entrega);
        return modelMapperDTODeEntrega(entrega);
    }

    public Entrega getUmaEntrega(Long id) {
        Entrega entregaExiste = hasId(id);
        return entregaExiste;
    }

    public EntregaModelDTO modelMapperDTODeEntrega(Object entrega) {/*
                                                                     * Este Metodo recebe uma Entidade Entrega e retorna
                                                                     * o DTO
                                                                     * entrega usando a biblioteca ModelMapper.
                                                                     */
        EntregaModelDTO entregaModelDTO = modelMapper.map(entrega, EntregaModelDTO.class);
        return entregaModelDTO;
    }

    public List<EntregaModelDTO> modelMapperListDeEntrega(List<Entrega> entregas) {
        /*
         * 1º Recebo a lista de entrega q vira do FindALL()
         * 2º converto em Stream e Uso Map que aplica uma função em todos elemento da
         * Stream e retorna um outro resultado.
         * 3º Como paramentro do Usaremos o MetaReference passando o
         * this::this::modelMapperDTODeEntrega
         * que transforma em 1 stream de entrega p 1 stream de EntregaModelDTO.
         * 4º Temos q retornar uma lista, Usando o Collect para transformar novamente um
         * uma Lista
         */
        return entregas.stream()
                .map(this::modelMapperDTODeEntrega)
                .collect(Collectors.toList());
    }

}
