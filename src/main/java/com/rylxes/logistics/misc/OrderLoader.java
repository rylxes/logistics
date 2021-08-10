package com.rylxes.logistics.misc;

import com.rylxes.logistics.models.entities.Order;
import com.rylxes.logistics.models.entities.OrderProcesses;
import com.rylxes.logistics.models.repositories.OrderProcessRepository;
import com.rylxes.logistics.models.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderLoader implements CommandLineRunner {

    public final OrderRepository orderRepository;
    public final OrderProcessRepository orderProcessRepository;

    public OrderLoader(OrderRepository orderRepository, OrderProcessRepository orderProcessRepository) {
        this.orderRepository = orderRepository;
        this.orderProcessRepository = orderProcessRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadOrders();
    }

    private void loadOrders() {
        if (orderRepository.count() == 0) {
            Order one = orderRepository.save(
                    Order.builder()
                            .name("John doe")
                            .quantity(2)
                            .createdBy(1)
                            .address("John Address")
                            .packageContent("Buy eggs from market")
                            .build()
            );
            orderProcessRepository.save(
                    OrderProcesses.builder()
                            .orderId(one.getId())
                            .status(OrderStatus.PICKED_UP)
                            .build()
            );


            Order two = orderRepository.save(
                    Order.builder()
                            .name("Jane dine")
                            .quantity(5)
                            .createdBy(1)
                            .address("Jane Address")
                            .packageContent("100 pumpkins")
                            .build()
            );

            orderProcessRepository.save(
                    OrderProcesses.builder()
                            .orderId(two.getId())
                            .status(OrderStatus.PICKED_UP)
                            .build()
            );
            log.info("Sample Orders Loaded");
        }
    }
}
