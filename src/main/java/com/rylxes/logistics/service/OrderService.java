package com.rylxes.logistics.service;

import com.rylxes.logistics.misc.OrderStatus;
import com.rylxes.logistics.models.dto.OrderDTO;
import com.rylxes.logistics.models.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderService {

    Page<Order> getOrders(int pageNo, int pageSize);

    Order getOrderId(Long id);

    Order insert(OrderDTO order);

    Order updateOrder(Long id, OrderDTO order);

    void updateOrderStatus(Long id, OrderStatus orderStatus);

    void deleteOrder(Long orderId);
}
