package com.example.demo.repositories;

import com.example.demo.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, String> {
}
