package com.rylxes.logistics.controller;


import com.rylxes.logistics.misc.OrderStatus;
import com.rylxes.logistics.models.dto.OrderDTO;
import com.rylxes.logistics.models.entities.Order;
import com.rylxes.logistics.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
@Api(value = "Orders", description = "Place an Order")
public class OrdersController {

    OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    //The function receives a GET request, processes it and gives back a list of Order as a response.
    @ResponseBody
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Page<Order>> getAllOrders(int pageNo, int pageSize) {
        Page<Order> orders = orderService.getOrders(pageNo, pageSize);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of Order as a response.
    @ResponseBody
    @GetMapping(value = "/{orderId}", produces = "application/json")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrderId(orderId), HttpStatus.OK);
    }


    //The function receives a POST request, processes it, creates a new Order and saves it to the database, and returns a resource link to the created order.
    @ResponseBody
    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody OrderDTO order) {
        return new ResponseEntity<>(orderService.insert(order), HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Order with the specified Id and returns the updated Order
    @ResponseBody
    @PutMapping(value = "/{orderId}", produces = "application/json")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Long orderId, @Valid @RequestBody OrderDTO order) {
        return new ResponseEntity<>(orderService.updateOrder(orderId, order), HttpStatus.OK);
    }


    //The function receives a PUT request, updates the Order with the order status
    @ResponseBody
    @PutMapping(value = "/updateStatus", produces = "application/json")
    public ResponseEntity<Order> updateOrder(@RequestHeader Long orderId, @RequestHeader OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>(orderService.getOrderId(orderId), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Order with the specified Id.
    @ResponseBody
    @DeleteMapping(value = "/{orderId}", produces = "application/json")
    public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
