package com.example.demo.repositories;

import com.example.demo.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, Long> {
}
