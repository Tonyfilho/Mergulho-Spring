package com.tony.clientes.trainingAlgaWorks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.tony.clientes.trainingAlgaWorks.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

/*Esta Classe é o DTO da classe Entrega, usamos este padrão por segurança Não 
  expor a nossa ENTIDADE
 * Desta forma poderemos tb formatar os dados, ao invez de passar todas os atributos com o todos 
 * os atributos de Cliente que temos em Entrega, podemos passar uma string onde um construtor receba os dados do Objeto Cliente 
 * e formate em um padrão somente nomeCliente onde ja recebe cliente { nome,  email, telefone} 
 * da mesma forma o o DestinatarioMOdel q ao invez de mandar o Objeto do Destinario receberemos uma 
 */
@Getter
@Setter
public class EntregaModelDTO {


    private Long id;
    private String nomeCliente;
    private DestinatarioModelDTO destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
