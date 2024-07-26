package com.wrkspotassignment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.wrkspotassignment.model.dto.Customer;

import java.util.ArrayList;
import java.util.List;

public class WrkSpotAssignment2 {
    public static void main(String [] args) {
        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);

        Customer customer2 = new Customer();
        customer1.setCustomerId(2L);

        List<Customer> A = new ArrayList<>();
        A.add(customer1);
        List<Customer> B = new ArrayList<>();
        B.add(customer2);

        System.out.println(getListA(A, B));
    }

    private static List<Customer> getListA(List<Customer> A, List<Customer> B) {
        return A;
    }

    private static List<Customer> getListB(List<Customer> A, List<Customer> B) {
        return B;
    }

    private static List<Customer> getBothList(List<Customer> A, List<Customer> B) {
        A.addAll(B);
        return A;
    }


}
