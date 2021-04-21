package com.arthur.delivery.services;

import com.arthur.delivery.entidades.Entregador;
import com.arthur.delivery.repositories.EntregadorRepository;
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
public class EntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public List<Entregador> buscarTodos() {
        return entregadorRepository.findAll();
    }

    public Entregador buscarPorId(Long id) {
        Optional<Entregador> obj = entregadorRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));// tenta fazer o get, se nao conseguir, lança excecao
    }

    public Entregador inserir(Entregador obj) {
        return entregadorRepository.save(obj);
    }

    public void deletar(Long id) {
        try {
            entregadorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Entregador atualizar(Long id, Entregador obj) {
        try {
            Entregador entidade = entregadorRepository.getOne(id);//deixa o endereco preparado para ser manipulado, sem ir no banco de dados
            atualizarDados(obj, entidade);
            return entregadorRepository.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(Entregador obj, Entregador entidade) {
        entidade.setNome(obj.getNome());
        entidade.setEmail(obj.getEmail());
        entidade.setTelefone(obj.getTelefone());
        entidade.setCNH(obj.getCNH());
    }
}
