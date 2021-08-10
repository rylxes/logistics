package com.rylxes.logistics.models.repositories;

import com.rylxes.logistics.models.entities.Order;
import com.rylxes.logistics.models.entities.OrderProcesses;
import org.springframework.data.repository.CrudRepository;

public interface OrderProcessRepository extends CrudRepository<OrderProcesses, Long> {
}
