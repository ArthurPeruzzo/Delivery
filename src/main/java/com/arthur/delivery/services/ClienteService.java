package com.arthur.delivery.services;

import com.arthur.delivery.entidades.Cliente;
import com.arthur.delivery.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));// tenta fazer o get, se nao conseguir, lança excecao
    }

    public Cliente inserir(Cliente obj){
        return clienteRepository.save(obj);
    }

    public void deletar(Long id){
        try {
            clienteRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public Cliente atualizar(Long id, Cliente obj){
        try{
            Cliente entidade = clienteRepository.getOne(id);//deixa o usuario preparado para ser manipulado, sem ir no banco de dados
            atualizarDados(obj, entidade);
            return clienteRepository.save(entidade);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(Cliente obj, Cliente entidade){
        entidade.setNome(obj.getNome());
        entidade.setEmail(obj.getEmail());
        entidade.setTelefone(obj.getTelefone());
        entidade.setCPF(obj.getCPF());
    }
}
