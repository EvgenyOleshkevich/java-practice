package com.example.demo.Repositories;

import com.example.demo.models.Variable;
import org.springframework.data.repository.CrudRepository;


public interface VariableRepo extends CrudRepository<Variable, String>{
}
