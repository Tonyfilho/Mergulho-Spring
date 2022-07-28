package com.tony.clientes.trainingAlgaWorks.api.modelDTO.outPutData;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.tony.clientes.trainingAlgaWorks.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

/*Esta Classe é o DTO da classe Entrega, usamos este padrão por segurança Não 
  expor a nossa ENTIDADE
 * Desta forma poderemos tb formatar os dados, ao invez de passar todas os atributos com o todos 
 * os atributos de Cliente que temos em Entrega, podemos passar uma string onde um construtor
 *  receba os dados do Objeto Cliente , ou um Classe chamada Cliente.
 * e formate em um padrão somente nomeCliente onde ja recebe cliente { nome,  email, telefone} 
 * da mesma forma o o DestinatarioMOdel q ao invez de mandar o Objeto do Destinario receberemos uma 
 */
@Getter
@Setter
public class EntregaOutputModelDTO {

  private Long id;
  private ClienteOutputModelDTO cliente;
  private DestinatarioOutputModelDTO destinatario;
  private BigDecimal taxa;
  private StatusEntrega status;
  private OffsetDateTime dataPedido;
  private OffsetDateTime dataFinalizacao;
}
