package com.arthur.delivery.resources;

import com.arthur.delivery.entidades.Restaurante;
import com.arthur.delivery.services.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteResource {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> buscarTodos() {
        List<Restaurante> list = restauranteService
                .buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        Restaurante obj = restauranteService
                .buscarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")// o {id} faz com que possa colocar um id na url;
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        restauranteService
                .deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Restaurante> atualizar(@RequestBody Restaurante obj, @PathVariable Long id) {
        obj = restauranteService.atualizar(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
