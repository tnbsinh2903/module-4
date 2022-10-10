package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "withdraws")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Withdraw extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    private BigDecimal transactionAmount;

    public Withdraw(Customer customer, BigDecimal transactionAmount) {
         this.customer = customer;
        this.transactionAmount = transactionAmount;
    }
}
