package com.rylxes.logistics.models.repositories;

import com.rylxes.logistics.models.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaSpecificationExecutor<Order>, CrudRepository<Order, Long> {
    Page<Order> findAll(Pageable pageable);
}
