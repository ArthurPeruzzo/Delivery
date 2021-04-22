package com.arthur.delivery.services;

import com.arthur.delivery.entidades.Pedido;
import com.arthur.delivery.repositories.PedidoRepository;
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
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));// tenta fazer o get, se nao conseguir, lança excecao
    }

    public Pedido inserir(Pedido obj) {
        return pedidoRepository.save(obj);
    }

    public void deletar(Long id) {
        try {
            pedidoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Pedido atualizar(Long id, Pedido obj) {
        try {
            Pedido entidade = pedidoRepository.getOne(id);//deixa o endereco preparado para ser manipulado, sem ir no banco de dados
            atualizarDados(obj, entidade);
            return pedidoRepository.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(Pedido obj, Pedido entidade) {
        entidade.setHoraPedido(obj.getHoraPedido());
        entidade.setValorEntrega(obj.getValorEntrega());
    }
}
