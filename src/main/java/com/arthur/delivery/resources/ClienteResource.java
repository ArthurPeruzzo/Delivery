package com.arthur.delivery.resources;

import com.arthur.delivery.entidades.Cliente;
import com.arthur.delivery.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos(){
        List<Cliente> list = clienteService.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        Cliente obj= clienteService.buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente obj){ //anotation para que o usuario passado seja convertido para objeto
        obj = clienteService.inserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //uri serve para sair o codigo 201 http
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping (value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente obj, @PathVariable Long id){
        obj = clienteService.atualizar(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
