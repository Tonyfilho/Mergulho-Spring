package com.tony.clientes.trainingAlgaWorks.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tony.clientes.trainingAlgaWorks._validationGroups.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // por ser o ID q será comparado,t emos q tb por a anotação
                                                  // @EqualsAndHashCode.Include

public class Cliente {
    /*
     * Todo beanValidator Ex: @NotNull(Groups = Defaut.class) por default já não é
     * declarado.
     * Criamos uma Interface onde teremos o customs group dentro de
     * _validationGroups
     * para resolvermos problemas de validações duplas, quando existe @Valid lá na
     * relações
     * entre Entrega e Cliente, Pois as validações DEFAULT só funcional
     * para as entidade sem relacionamento. *
     * para resolver criamos o nosso groups = ValidationGroups.ClienteID.class
     */
    @NotNull(groups = ValidationGroups.CustomClienteID.class)
    @EqualsAndHashCode.Include // esta é a anotação de exclusividade do Equals and HashCode, pois se o ID for
                               // Iqual o Objeto será true, sem a necessidade de verificar o restante
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome")
    @NotBlank // impede q alem de não poder ser NULL tb não pode ser Vazio
    @Size(max = 60)
    String nome;

    @NotBlank
    @Size(max = 20)
    String telefone;

    @NotBlank
    @Size(max = 200)
    @Email // para validações de email
    String email;

}
