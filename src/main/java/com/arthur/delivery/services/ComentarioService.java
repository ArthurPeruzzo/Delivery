package com.arthur.delivery.services;

import com.arthur.delivery.entidades.Comentario;
import com.arthur.delivery.entidades.Pedido;
import com.arthur.delivery.repositories.ComentarioRepository;
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
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> buscarTodos() {
        return comentarioRepository.findAll();
    }

    public Comentario buscarPorId(Long id) {
        Optional<Comentario> obj = comentarioRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));// tenta fazer o get, se nao conseguir, lança excecao
    }

    public Comentario inserir(Comentario obj) {
        return comentarioRepository.save(obj);
    }

    public void deletar(Long id) {
        try {
            comentarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Comentario atualizar(Long id, Comentario obj) {
        try {
            Comentario entidade = comentarioRepository.getOne(id);//deixa o endereco preparado para ser manipulado, sem ir no banco de dados
            atualizarDados(obj, entidade);
            return comentarioRepository.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(Comentario obj, Comentario entidade) {
        entidade.setComentario(obj.getComentario());
        entidade.setNota(obj.getNota());
    }
}
