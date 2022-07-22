package com.tony.clientes.trainingAlgaWorks.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  
    @Valid // usa-se em Relacionamentos de Entidade com Entidades para q o @NotNUll da entidade Cliente veja funcionar.
    @ManyToOne //criando a relação via objeto com cliente ou seja foreingKey do modelo Relacional
    @NotNull
    private Cliente cliente;

    @Embedded  
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;
    
    /**Conta esta anotação o POSTMAN não consegue enviar dados
     *   e os mesmo são SETADOS no service*/
    @JsonProperty(access = Access.READ_ONLY)
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private StatusEntrega  statusEntrega;
    
    
    
    @JsonProperty(access = Access.READ_ONLY)
    @Column(name="data_pedido")
    private LocalDateTime dataPedido;
    
    
    @JsonProperty(access = Access.READ_ONLY)
    @Column(name="data_finalizado")
    private LocalDateTime datafinalizacao;













    
}
