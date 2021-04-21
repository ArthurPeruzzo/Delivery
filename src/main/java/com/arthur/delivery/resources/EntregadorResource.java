package com.arthur.delivery.resources;

import com.arthur.delivery.entidades.Entregador;
import com.arthur.delivery.services.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/entregadores")
public class EntregadorResource {

    @Autowired
    private EntregadorService entregadorService;

    @GetMapping
    public ResponseEntity<List<Entregador>> buscarTodos() {
        List<Entregador> list = entregadorService
                .buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Entregador> buscarPorId(@PathVariable Long id) {
        Entregador obj = entregadorService
                .buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Entregador> inserir(@RequestBody Entregador obj) { //anotation para que o usuario passado seja convertido para objeto
        obj = entregadorService
                .inserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //uri serve para sair o codigo 201 http
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        entregadorService
                .deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Entregador> atualizar(@RequestBody Entregador obj, @PathVariable Long id) {
        obj = entregadorService.atualizar(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
