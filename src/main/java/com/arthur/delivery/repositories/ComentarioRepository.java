package com.arthur.delivery.repositories;

import com.arthur.delivery.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
