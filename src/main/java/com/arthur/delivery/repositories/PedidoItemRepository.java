package com.arthur.delivery.repositories;

import com.arthur.delivery.entidades.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {

}
