package com.arthur.delivery.resources;

import com.arthur.delivery.entidades.Pedido;
import com.arthur.delivery.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTodos() {
        List<Pedido> list = pedidoService
                .buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido obj = pedidoService
                .buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Pedido> inserir(@RequestBody Pedido obj) { //anotation para que o usuario passado seja convertido para objeto
        obj = pedidoService
                .inserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //uri serve para sair o codigo 201 http
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService
                .deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> atualizar(@RequestBody Pedido obj, @PathVariable Long id) {
        obj = pedidoService.atualizar(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
