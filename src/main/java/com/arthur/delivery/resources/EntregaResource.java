package com.arthur.delivery.resources;

import com.arthur.delivery.entidades.Entrega;
import com.arthur.delivery.services.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaResource {

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public ResponseEntity<List<Entrega>> buscarTodos() {
        List<Entrega> list = entregaService
                .buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Entrega> buscarPorId(@PathVariable Long id) {
        Entrega obj = entregaService
                .buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Entrega> inserir(@RequestBody Entrega obj) { //anotation para que o usuario passado seja convertido para objeto
        obj = entregaService
                .inserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //uri serve para sair o codigo 201 http
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        entregaService
                .deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Entrega> atualizar(@RequestBody Entrega obj, @PathVariable Long id) {
        obj = entregaService.atualizar(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
