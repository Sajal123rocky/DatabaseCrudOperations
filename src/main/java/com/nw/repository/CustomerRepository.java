package com.nw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nw.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
