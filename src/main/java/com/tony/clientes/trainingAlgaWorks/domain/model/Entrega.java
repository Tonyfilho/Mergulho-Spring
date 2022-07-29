package com.tony.clientes.trainingAlgaWorks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    /*
     * Faremos o mapeamento inverso, pois lá em emcorrencia temo ManyToOne, ou seja
     * Entrega receberá uma Lista de ocorrencia.
     * Obs: Temos q iniciar a lista vazia, mas tem q ser inicializada iqual no
     * angular.
     * 
     */
    @OneToMany(mappedBy = "entrega") /*
                                      * Temos que adcionar uma propriedade que Mapea a Dona da relação, do lado
                                      * Inverso, temos que dar no nome do atributo que manda os dados
                                      */
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    public Ocorrencia AdcionarOcorrenciaDescricao(String descricao) { /*
                                                                       * Criando para ser usando lá em OcorrenciaService
                                                                       * 1º Cria uma instacia da Classe Ocorrencia
                                                                       * 2º Seta o valor para a correncia
                                                                       * 3º Setar a data de registro,
                                                                       * 4º Setar a entrega usando o THIS da classe,
                                                                       * 5º setar o Atributo Local ocorrencia, passando
                                                                       * a var(...) como paramentro.
                                                                       * 6º retorna a ocorrencia
                                                                       */
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);
        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;
    }

}
