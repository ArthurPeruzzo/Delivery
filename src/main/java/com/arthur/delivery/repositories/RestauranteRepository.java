package com.arthur.delivery.repositories;

import com.arthur.delivery.entidades.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
