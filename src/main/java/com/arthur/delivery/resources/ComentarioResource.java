package com.arthur.delivery.resources;

import com.arthur.delivery.entidades.Comentario;
import com.arthur.delivery.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioResource {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<Comentario>> buscarTodos() {
        List<Comentario> list = comentarioService
                .buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id) {
        Comentario obj = comentarioService
                .buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Comentario> inserir(@RequestBody Comentario obj) { //anotation para que o usuario passado seja convertido para objeto
        obj = comentarioService
                .inserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //uri serve para sair o codigo 201 http
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comentarioService
                .deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Comentario> atualizar(@RequestBody Comentario obj, @PathVariable Long id) {
        obj = comentarioService.atualizar(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
