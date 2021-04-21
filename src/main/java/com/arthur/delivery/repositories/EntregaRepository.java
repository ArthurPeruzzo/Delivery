package com.arthur.delivery.repositories;

import com.arthur.delivery.entidades.Entrega;
import com.arthur.delivery.entidades.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
