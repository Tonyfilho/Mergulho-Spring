package com.tony.clientes.trainingAlgaWorks.domain.services;


import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony.clientes.trainingAlgaWorks._exceptionHandle.BusinessException;
import com.tony.clientes.trainingAlgaWorks.domain.model.Cliente;
import com.tony.clientes.trainingAlgaWorks.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor //com lombok n precisamo usar @Autowired
public class ClienteService {

  ClienteRepository clienteRepository;

/** -> 
 * @param id
 * @return
 */
public Cliente  getClienteById(Long id) {
  //  return clienteRepository.findById(id).isPresent() ? ResponseEntity.ok(clienteRepository.findById(id).get()): ResponseEntity.notFound().build();
  return clienteRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("404 Resource not found or not exist", 1));
    
}

public Cliente atualizaClienteService(Long id, Cliente cliente) {
    Cliente clienteAtualizado = getClienteById(id);
    BeanUtils.copyProperties(cliente, clienteAtualizado, "id");
    return clienteRepository.save(clienteAtualizado);
}

//metodo para salvar
@Transactional /*faz com q a class tenha preferencia em sua transações e persistencia com a DB, caso haja Erros aqui, nada será salvo na DB*/
public Cliente saveCliente(Cliente cliente) {
  /****************************************Criando regra para Não salvar email duplicados */
 // boolean emailAlreadyUsed =   clienteRepository.findByEmail(cliente.getEmail()).isPresent();
 /**Ou usar a Api de Stream do JAVA com lambda, ou seja o se email q temo na DB for diferente do email
  * que estamos recebendo então podemos cadastrar
  */
  boolean emailAlreadyUsed =   clienteRepository.findByEmail(cliente.getEmail())
  .stream().anyMatch(emailExistente -> !emailExistente.equals(cliente) );
   if (emailAlreadyUsed) {
     throw new BusinessException("Email already exist ");
   }
   return clienteRepository.save(cliente);
}

//metodo para exluir q é void
@Transactional
public void deleteCliente(Long id) {
  clienteRepository.deleteById(id);
}
    
}// end class
