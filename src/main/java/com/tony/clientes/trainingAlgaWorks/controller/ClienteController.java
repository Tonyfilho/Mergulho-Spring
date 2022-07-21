package com.tony.clientes.trainingAlgaWorks.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
// import javax.validation.Valid;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tony.clientes.trainingAlgaWorks.model.Cliente;
import com.tony.clientes.trainingAlgaWorks.repository.ClienteRepository;
import com.tony.clientes.trainingAlgaWorks.services.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor // cria uns construtor para a interface ClienteRepositories
@RequestMapping("/clientes") //Evita q seja repetido o caminha /clientes em todos no metodos do controller
public class ClienteController {
    /**
     * Podemos Usar o Autowired ou construtor, o deixa o Lomboq cria este construtor
     * invisível para nós usando AllArgsConstructors
     * #@Autowired
     * ClienteRepositories clienteRepositories;
     */

    ClienteRepository clienteRepositories;

    ClienteService clienteService;

    @GetMapping()
    public List<Cliente> listarClientes() {
        return clienteRepositories.findAll();

    }
    /**Faz o retorno pelo nome e retorna uma LIST */
    @GetMapping("/Contain/{nome}")
    public List<Cliente> listarClienteByNameContain(@PathVariable String nome, HttpServletResponse response) {
        return clienteRepositories.findByNomeContaining(nome);

    }
    /**Faz o retorno pelo nome , Obs o FindByNome TEM q retornar um OPTIONAL*/
    @GetMapping("/{nome}")
    public ResponseEntity<Cliente> listarClienteByName(@PathVariable String nome, HttpServletResponse response) {        
        Optional<Cliente> clienteName = clienteRepositories.findByNome(nome);
       return clienteName.isPresent() ? ResponseEntity.ok(clienteName.get()) : ResponseEntity.notFound().build();

    }
    /**Traz um Id */
    @GetMapping("/by/{id}")
    public ResponseEntity<Cliente> listarClienteById(@PathVariable Long id, HttpServletResponse response) {        
        Optional<Cliente> clienteName = clienteRepositories.findById(id);
       return clienteName.isPresent() ? ResponseEntity.ok(clienteName.get()) : ResponseEntity.notFound().build();

    }
    
    /**Faz um Post na DB e retorna 1 cliente */

    @PostMapping()
    public ResponseEntity<Cliente> criarCliente( @RequestBody @Valid Cliente cliente, HttpServletResponse response) {
        Cliente clienteSalvo = clienteRepositories.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id , @RequestBody @Valid Cliente cliente) {
        // Cliente clienteAtualizado = clienteService.atualizaClienteService(id, cliente);
        // return ResponseEntity.status(HttpStatus.OK).body(clienteAtualizado);
        Optional<Cliente> clienteAtualizado = clienteRepositories.findById(id);
        if (clienteAtualizado.isPresent()) {                
            cliente.setId(id);   /**Atribuindo o mesmo Id o JPA  forçar uma atualização */
            clienteRepositories.save(cliente);
            return ResponseEntity.ok(cliente);            
        }        
         return  ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id , @RequestBody Cliente cliente) {
      
        Optional<Cliente> clienteAtualizado = clienteRepositories.findById(id);
        if (clienteAtualizado.isPresent()) {                
            cliente.setId(id);   /**Atribuindo o mesmo Id o JPA  forçar uma atualização */
            clienteRepositories.delete(cliente);
            return ResponseEntity.noContent().build();            
        }        
         return  ResponseEntity.notFound().build();

    }



   


}
