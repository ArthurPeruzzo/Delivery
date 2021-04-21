package com.arthur.delivery.services;

import com.arthur.delivery.entidades.Entrega;
import com.arthur.delivery.entidades.Entregador;
import com.arthur.delivery.repositories.EntregaRepository;
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
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public List<Entrega> buscarTodos() {
        return entregaRepository.findAll();
    }

    public Entrega buscarPorId(Long id) {
        Optional<Entrega> obj = entregaRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));// tenta fazer o get, se nao conseguir, lança excecao
    }

    public Entrega inserir(Entrega obj) {
        return entregaRepository.save(obj);
    }

    public void deletar(Long id) {
        try {
            entregaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Entrega atualizar(Long id, Entrega obj) {
        try {
            Entrega entidade = entregaRepository.getOne(id);//deixa o endereco preparado para ser manipulado, sem ir no banco de dados
            atualizarDados(obj, entidade);
            return entregaRepository.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(Entrega obj, Entrega entidade) {
        entidade.setHorarioSaida(obj.getHorarioSaida());
        entidade.setHorarioEntrega(obj.getHorarioEntrega());
        entidade.setAvaliacao(obj.getAvaliacao());
    }
}
