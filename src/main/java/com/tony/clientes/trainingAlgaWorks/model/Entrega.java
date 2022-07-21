package com.tony.clientes.trainingAlgaWorks.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

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

    @NotBlank
    @ManyToOne //criando a relação via objeto com cliente ou seja foreingKey do modelo Relacional
    private Cliente cliente;

    @Embedded
    private Destinatario destinaratio;

    @NotBlank
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private StatusEntrega  statusEntrega;

    @NotBlank
    private LocalDateTime dataPedido;
    
    private LocalDateTime datafinalizacao;













    
}
