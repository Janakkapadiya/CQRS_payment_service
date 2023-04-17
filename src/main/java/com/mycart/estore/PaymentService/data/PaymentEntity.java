package com.mycart.estore.PaymentService.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class PaymentEntity implements Serializable{

    private static final long serialVersionUID = 7929803267872140552L;

    @Id
    private String paymentId;

    @Column
    public String orderId;
}
