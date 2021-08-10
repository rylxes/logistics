package com.rylxes.logistics.models.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rylxes.logistics.misc.OrderStatus;
import com.rylxes.logistics.models.entities.Order;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@ToString
@Getter
@Setter
@Builder
public class OrderProcessesDTO {


    private Long id;
    private Order order;
    private Long orderId;
    private OrderStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
