package com.tony.clientes.trainingAlgaWorks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tony.clientes.trainingAlgaWorks.api.model.EntregaModelDTO;
import com.tony.clientes.trainingAlgaWorks.domain.model.Entrega;
import com.tony.clientes.trainingAlgaWorks.domain.repository.EntregaRepository;
import com.tony.clientes.trainingAlgaWorks.domain.services.EntregaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas")
public class EntregaController {

    EntregaService entregaService;
    EntregaRepository entregaRepository;

    /** Pedir um entrega */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) /*
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

    public EntregaModelDTO solicitarEntrega(@Valid @RequestBody Entrega entrega) {
        return entregaService.getEntrega(entrega);
    }

    @GetMapping()
    public List<EntregaModelDTO> listarTodasEntregas() {
        return entregaService.modelMapperListDeEntrega(entregaRepository.findAll());
    }

    /**************************************************
     * OBS******************
     * @GetMapping("/{id}")
     * 
     * @ResponseStatus(HttpStatus.OK)
     *                                public Entrega listaUmaentrega(@PathVariable
     *                                Long id ) {
     *                                return entregaService.getUmaEntrega(id);
     *                                }
     */

    /******************************************************
     * OBS****************
     * Como passamos a usar TDOs usaremos outro GetMapping("/{id}")
     * listaUmaentrega(), retornando convertendo de entrega to entregaModelDTO
     * 
     * @GetMapping("/{id}")
     * public ResponseEntity<Entrega> listaUmaentrega(@PathVariable Long id) {
     * return entregaRepository.findById(id)
     * .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
     * }
     */

    /*
     * Criaremos dentro do nosso Service o conversor de Entidade para DTO, recebe um
     * entidade entrega e devolve entregaModelDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntregaModelDTO> listaUmaentrega(@PathVariable Long id) {
        return entregaRepository.findById(id)
                /****** Forma reduzida */
                /*
                 * .map(entrega -> {
                 * EntregaModelDTO entregaModelDTO = entregaService.modelMapperDTO(entrega);
                 * return ResponseEntity.ok(entregaModelDTO);
                 * }).orElse(ResponseEntity.notFound().build());
                 */
                .map(entrega -> {
                    return ResponseEntity.ok(entregaService.modelMapperDTODeEntrega(entrega));
                }).orElse(ResponseEntity.notFound().build());
    }
}
