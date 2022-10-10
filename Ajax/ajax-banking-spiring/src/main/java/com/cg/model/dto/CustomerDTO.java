package com.cg.model.dto;

import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CustomerDTO {

    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private BigDecimal balance;

    private LocationRegionDTO locationRegion;


    public Customer toCustomer (LocationRegion locationRegion){
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setEmail(email)
                .setBalance(balance)
                .setLocationRegion(locationRegion);
    }

}
