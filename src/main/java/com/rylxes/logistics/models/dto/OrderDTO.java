package com.rylxes.logistics.models.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rylxes.logistics.models.entities.Order;
import com.rylxes.logistics.models.entities.OrderProcesses;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@ToString
public class OrderDTO {


    private Long id;
    private Integer quantity;
    private String name;
    private String address;
    private String packageContent;
    private List<OrderProcessesDTO> orderProcessesList;
    private Integer createdBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;




}
