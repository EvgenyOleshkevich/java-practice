package com.example.demo.repositories;

import com.example.demo.models.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepo  extends CrudRepository<Seller, String> {
}
