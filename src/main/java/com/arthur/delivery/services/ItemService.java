package com.arthur.delivery.services;

import com.arthur.delivery.entidades.Entrega;
import com.arthur.delivery.entidades.Item;
import com.arthur.delivery.repositories.EntregaRepository;
import com.arthur.delivery.repositories.ItemRepository;
import com.arthur.delivery.services.exceptions.DatabaseException;
import com.arthur.delivery.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> buscarTodos() {
        return itemRepository.findAll();
    }

    public Item buscarPorId(Long id) {
        Optional<Item> obj = itemRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));// tenta fazer o get, se nao conseguir, lança excecao
    }

    public Item inserir(Item obj) {
        return itemRepository.save(obj);
    }

    public void deletar(Long id) {
        try {
            itemRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Item atualizar(Long id, Item obj) {
        try {
            Item entidade = itemRepository.getOne(id);//deixa o endereco preparado para ser manipulado, sem ir no banco de dados
            atualizarDados(obj, entidade);
            return itemRepository.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(Item obj, Item entidade) {
        entidade.setNome(obj.getNome());
        entidade.setPreco(obj.getPreco());
    }
}
