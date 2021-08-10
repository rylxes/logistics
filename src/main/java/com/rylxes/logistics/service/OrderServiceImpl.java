package com.rylxes.logistics.service;

import com.rylxes.logistics.misc.OrderStatus;
import com.rylxes.logistics.models.dto.OrderDTO;
import com.rylxes.logistics.models.dto.OrderProcessesDTO;
import com.rylxes.logistics.models.entities.Order;
import com.rylxes.logistics.models.entities.OrderProcesses;
import com.rylxes.logistics.models.repositories.OrderProcessRepository;
import com.rylxes.logistics.models.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    public final OrderRepository orderRepository;
    public final OrderProcessRepository orderProcessRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderProcessRepository orderProcessRepository) {
        this.orderRepository = orderRepository;
        this.orderProcessRepository = orderProcessRepository;
    }

    /**
     * Maps the Page {@code entities} of <code>T</code> type which have to be mapped as input to {@code dtoClass} Page
     * of mapped object with <code>D</code> type.
     *
     * @param <D>      - type of objects in result page
     * @param <T>      - type of entity in <code>entityPage</code>
     * @param entities - page of entities that needs to be mapped
     * @param dtoClass - class of result page element
     * @return page - mapped page with objects of type <code>D</code>.
     * @NB <code>dtoClass</code> must has NoArgsConstructor!
     */
    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }

    private OrderDTO convertToDto(Order order) {
        OrderDTO postDto = modelMapper.map(order, OrderDTO.class);
        return postDto;
    }

    @Override
    public Page<Order> getOrders(int pageNo, int pageSize) {
        pageNo = pageNo - 1;
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return orderRepository.findAll(paging);
    }

    @Override
    public Order getOrderId(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order insert(Order order) {
        order.setCreatedBy(1); // we assume user with order id 1 is the one making the request
        return orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long id, Order order) {
        Order orderFromDb = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        log.info(orderFromDb.toString());
        orderFromDb.setName(order.getName());
        orderFromDb.setQuantity(order.getQuantity());
        orderFromDb.setAddress(order.getAddress());
        orderFromDb.setPackageContent(order.getPackageContent());
        orderRepository.save(orderFromDb);

    }


    @Override
    public void updateOrderStatus(Long id, OrderStatus orderStatus) {

        ArrayList<OrderStatus> uniqueStatus = new ArrayList<>();
        uniqueStatus.add(OrderStatus.PICKED_UP);
        uniqueStatus.add(OrderStatus.DELIVERED);


        Order orderFromDb = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderStatus> fromDB = orderFromDb.getOrderProcessesList().stream().map(OrderProcesses::getStatus).collect(Collectors.toList());
        uniqueStatus.retainAll(fromDB);
        if (!uniqueStatus.isEmpty() && uniqueStatus.contains(orderStatus)) {
            throw new RuntimeException("This Order Status Already exist");
        }

        OrderProcesses orderProcesses = new OrderProcesses();
        orderProcesses.setOrderId(orderFromDb.getId());
        orderProcesses.setStatus(orderStatus);
        orderProcessRepository.save(orderProcesses);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order orderFromDb = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(orderFromDb);
    }
}
