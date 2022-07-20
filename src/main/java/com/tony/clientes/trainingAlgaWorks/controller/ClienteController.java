package com.tony.clientes.trainingAlgaWorks.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tony.clientes.trainingAlgaWorks.model.Cliente;
import com.tony.clientes.trainingAlgaWorks.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor // cria uns construtor para a interface ClienteRepositories
public class ClienteController {
    /**
     * Podemos Usar o Autowired ou construtor, o deixa o Lomboq cria este construtor
     * invisível para nós usando AllArgsConstructors
     * #@Autowired
     * ClienteRepositories clienteRepositories;
     */

    ClienteRepository clienteRepositories;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return clienteRepositories.findAll();

    }
    /**Faz o retorno pelo nome */
    @GetMapping("/{nome}")
    public ResponseEntity<Cliente> listarClienteByName(@PathVariable String nome, HttpServletResponse response) {
        Optional<Cliente> clienteNome = this.clienteRepositories.findByNome(nome);
        return clienteRepositories.findByNome(nome).ok(body);

    }

    /**
     *  @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscaPorUmaPessoa(@PathVariable Long codigo, HttpServletResponse response) {
        return pessoaRepository.findById(codigo).map(data -> ResponseEntity.ok(data))
                .orElse(ResponseEntity.notFound().build());

    }

     Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
   * return categoria.isPresent() ?
   * ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
     */

}
