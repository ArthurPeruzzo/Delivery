package com.arthur.delivery.repositories;

import com.arthur.delivery.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
