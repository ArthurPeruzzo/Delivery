package com.arthur.delivery.services;

import com.arthur.delivery.entidades.Pedido;
import com.arthur.delivery.entidades.Restaurante;
import com.arthur.delivery.repositories.PedidoRepository;
import com.arthur.delivery.repositories.RestauranteRepository;
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
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> buscarTodos() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarPorId(Long id) {
        Optional<Restaurante> obj = restauranteRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));// tenta fazer o get, se nao conseguir, lança excecao
    }

    public void deletar(Long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Restaurante atualizar(Long id, Restaurante obj) {
        try {
            Restaurante entidade = restauranteRepository.getOne(id);//deixa o endereco preparado para ser manipulado, sem ir no banco de dados
            atualizarDados(obj, entidade);
            return restauranteRepository.save(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(Restaurante obj, Restaurante entidade) {
        entidade.setNome(obj.getNome());
        entidade.setHoraAbertura(obj.getHoraAbertura());
        entidade.setHoraFechamento(obj.getHoraFechamento());
        entidade.setAvaliacao(obj.getAvaliacao());
        entidade.setCnpj(obj.getCnpj());
        entidade.setTelefone(obj.getTelefone());
    }
}
