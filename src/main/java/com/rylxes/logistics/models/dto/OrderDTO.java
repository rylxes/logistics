package com.rylxes.logistics.models.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rylxes.logistics.models.entities.Order;
import com.rylxes.logistics.models.entities.OrderProcesses;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@ToString
public class OrderDTO {

    @NotNull(message = "Order quantity is required")
    private Integer quantity;
    @NotEmpty(message = "Order name is required")
    private String name;
    @NotEmpty(message = "Order address is required")
    private String address;
    @NotEmpty(message = "Order package content is required")
    private String packageContent;
    @ApiModelProperty(hidden = true, readOnly = true)
    private Integer createdBy;
    @ApiModelProperty(hidden = true, readOnly = true)
    private Timestamp createdAt;
    @ApiModelProperty(hidden = true, readOnly = true)
    private Timestamp updatedAt;


}
