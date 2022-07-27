package com.tony.clientes.trainingAlgaWorks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tony.clientes.trainingAlgaWorks.domain.model.Entrega;


@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    
}
