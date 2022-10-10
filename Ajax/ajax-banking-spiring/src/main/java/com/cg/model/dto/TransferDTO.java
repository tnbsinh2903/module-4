package com.cg.model.dto;

import com.cg.model.Customer;
import com.cg.model.Transfer;
import lombok.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDTO implements Validator {
    private Long id;
    private Long senderId;
    private String senderName;
     private Long recipientId;

    @NotNull(message = "The transfer amount is required")
    @DecimalMin(value = "49", message = "Transaction Amount must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Transaction Amount must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal transferAmount;

    private int fees;
    private BigDecimal feesAmount;
    private BigDecimal transactionAmount;


    @Override
    public boolean supports(Class<?> clazz) {
        return TransferDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransferDTO transferDTO = (TransferDTO) target;
        BigDecimal transferAmount = transferDTO.getTransferAmount();

        if (transferAmount != null) {
            if (transferAmount.toString().length() > 9) {
                errors.rejectValue("transferAmount", "transferAmount.length");
            }

            if (!transferAmount.toString().matches("(^$|[0-9]*$)")) {
                errors.rejectValue("transferAmount", "transferAmount.matches");
            }
        } else {
            errors.rejectValue("transferAmount", "transferAmount.null");
        }
    }

    public Transfer toTransfer(Customer sender, Customer recipient) {
        return new Transfer()
                .setId(id)
                .setSender(sender)
                .setRecipient(recipient)
                .setTransferAmount(transferAmount)
                .setFeesAmount(feesAmount)
                .setFees(fees)
                .setTransactionAmount(transactionAmount);
    }

}
