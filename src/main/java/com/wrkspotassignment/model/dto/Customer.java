package com.wrkspotassignment.model.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "customer")
@Getter@Setter
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "customer_id")
    private Long customerId;



    private String mobileNumber;
    private String firstName;
    private String lastName;
    private Double spendingLimit;


    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> address = new ArrayList<>();



}
