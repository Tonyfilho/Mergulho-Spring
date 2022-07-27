package com.tony.clientes.trainingAlgaWorks.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.tony.clientes.trainingAlgaWorks._validationGroups.ValidationGroups.CustomClienteID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    
   

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid // usa-se em Relacionamentos de Entidade com Entidades para q o @NotNUll da
           // entidade Cliente veja funcionar.
    @ConvertGroup(from = Default.class, to = CustomClienteID.class) /*
                                                                     * usa-se para falar com spring q temos um custom
                                                                     * Bean validation. Por ter
                                                                     * relacionamento isto diz para spring ignorar
                                                                     * os @NotNull da entidade e usar um
                                                                     * customisada. e convertemos o Default.class para o
                                                                     * CustomClienteID com este Bean criado.
                                                                     * O CustomClienteID.class.
                                                                     */
    @ManyToOne // criando a relação via objeto com cliente ou seja foreingKey do modelo
               // Relacional
    @NotNull
    private Cliente cliente;

    @Embedded
    @Valid
    @NotNull
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    /**
     * Conta esta anotação o POSTMAN não consegue enviar dados
     * e os mesmo são SETADOS no service
     */
    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "data_pedido")
    private OffsetDateTime dataPedido;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "data_finalizado")
    private OffsetDateTime datafinalizacao;

}
