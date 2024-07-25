package com.wrkspotassignment.model.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    private String type;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

}
