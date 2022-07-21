package com.tony.clientes.trainingAlgaWorks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //por ser o ID q será comparado,t emos q tb por a anotação @EqualsAndHashCode.Include

public class Cliente {
    @EqualsAndHashCode.Include // esta é a anotação de exclusividade do Equals and HashCode, pois se o ID for Iqual o Objeto será true, sem a necessidade de verificar o restante
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
