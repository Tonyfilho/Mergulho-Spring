package com.tony.clientes.trainingAlgaWorks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.tony.clientes.trainingAlgaWorks.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

 /**Criando um metodo de reforno de algo incomum, como o nome EXATO do cliente ,
  * Obs: Temos que usar camel Case como padrão no nome do Metodo, o Spring
   lista um padrão de nome possiveis Ex: findBy.... mais propriedade
 */
 Optional<Cliente> findByNome(String nome);

 /**Criando um metodo de retorno do nome com o LIKE onde pode se conter  */
 List<Cliente> findByNomeContaining(String nome);


/**criando um metodo para saber se ja existe ou não um email cadastrado,  retorna um Optional com algo ou nada */
Optional<Cliente>  findByEmail(String email);
    
}
