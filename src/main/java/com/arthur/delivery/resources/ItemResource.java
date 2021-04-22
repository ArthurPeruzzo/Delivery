package com.arthur.delivery.resources;

import com.arthur.delivery.entidades.Item;
import com.arthur.delivery.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/itens")
public class ItemResource {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> buscarTodos() {
        List<Item> list = itemService
                .buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        Item obj = itemService
                .buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Item> inserir(@RequestBody Item obj) { //anotation para que o usuario passado seja convertido para objeto
        obj = itemService
                .inserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //uri serve para sair o codigo 201 http
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemService
                .deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Item> atualizar(@RequestBody Item obj, @PathVariable Long id) {
        obj = itemService.atualizar(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
