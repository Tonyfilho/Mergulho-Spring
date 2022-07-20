package com.tony.clientes.trainingAlgaWorks.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tony.clientes.trainingAlgaWorks.model.Cliente;
import com.tony.clientes.trainingAlgaWorks.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

  ClienteRepository clienteRepository;

/** -> 
 * @param id
 * @return
 */
public ResponseEntity<Cliente>  getClienteById(Long id) {
//    return clienteRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
//   return clienteRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("404 Resource not found or not exist", 1));
  return clienteRepository.existsById(id) ? ResponseEntity.ok(clienteRepository.findAllById(id)): ResponseEntity.notFound().build();
  

    
}

public Cliente atualizaClienteService(Long id, Cliente cliente) {
    Cliente clienteAtualizado = getClienteById(id).getBody();
    BeanUtils.copyProperties(cliente, clienteAtualizado, "id");
    return clienteRepository.save(clienteAtualizado);
}


    
}
