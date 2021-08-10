package com.rylxes.logistics.models.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rylxes.logistics.misc.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
public class OrderProcesses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;


    @JsonBackReference
    @ToString.Exclude
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "orderId")
    private Order order;

    private Long orderId;


    @Enumerated(EnumType.STRING)
    private OrderStatus status;



    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;


    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

}
