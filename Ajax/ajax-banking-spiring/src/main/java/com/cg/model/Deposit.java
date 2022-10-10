package com.cg.model;

import com.cg.model.dto.DepositDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deposits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
 public class Deposit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    private BigDecimal transactionAmount;

    public Deposit(Customer customer, BigDecimal transactionAmount) {
        this.customer = customer;
        this.transactionAmount = transactionAmount;
    }

//    public DepositDTO toDepositDTO() {
//        return new DepositDTO()
//                .setCustomerId(id)
//                .setTransactionAmount(transactionAmount);
//
//    }


    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", customer=" + customer +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}
