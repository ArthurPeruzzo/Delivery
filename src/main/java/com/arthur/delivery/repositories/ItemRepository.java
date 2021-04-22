package com.arthur.delivery.repositories;

import com.arthur.delivery.entidades.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
