package com.rylxes.logistics.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rylxes.logistics.misc.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    private Integer quantity;
    private String name;
    private String address;

    @Column(name = "package_content")
    private String packageContent;



    @JsonManagedReference
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProcesses> orderProcessesList;



    @Column(name = "created_by")
    private Integer createdBy;


    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;



    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

}
