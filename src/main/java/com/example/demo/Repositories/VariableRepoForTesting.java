package com.example.demo.repositories;

import com.example.demo.models.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VariableRepoForTesting implements VariableRepo {

    private Map<String, Variable> data = new HashMap<String, Variable>();

    @Override
    public Variable save(Variable var){
        return var;
    }

    @Override
    public <S extends Variable> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Variable> findById(String s) {
        return  Optional.of(data.get(s));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Variable> findAll() {
        return null;
    }

    @Override
    public Iterable<Variable> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public void deleteById(String s) {
        data.remove(s);
    }

    @Override
    public void delete(Variable entity) {
        data.remove(entity.getName());
    }

    @Override
    public void deleteAll(Iterable<? extends Variable> entities) {

    }

    @Override
    public void deleteAll() {
        data.clear();
    }
}
