package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Transfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senderId")
   private Customer sender;

    @ManyToOne
    @JoinColumn(name = "recipientId")
   private Customer recipient;

    @Column(name = "transfer_Amount", precision = 12,nullable = false)
   private BigDecimal transferAmount;

    @Column(nullable = false)
   private int fees;

    @Column(name = "fees_Amount", precision = 12, nullable = false)
   private BigDecimal feesAmount;

    @Column(name = "transactionAmount", precision = 12, nullable = false)
   private BigDecimal transactionAmount;

    public Transfer(Customer sender, Customer recipient, BigDecimal transferAmount, int fees, BigDecimal feesAmount, BigDecimal transactionAmount) {
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }
}
