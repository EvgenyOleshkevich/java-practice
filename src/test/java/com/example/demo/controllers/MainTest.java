package com.example.demo.controllers;

import com.example.demo.repositories.VariableRepo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MainTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VariableRepo Vars;


    /*@Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {

        Variable var = new Variable("a", 7);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/add")
                .buildAndExpand(var)
                .toUri();

        Variable res = ResponseEntity.created(uri).body(var).getBody();
        assertEquals(res.getName(), var.getName());

    }*/


    /*@Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Variable var = new Variable("a", 5);
        entityManager.persist(var);
        entityManager.flush();

        // when
        Variable found = Vars.findById(var.getName()).get();

        // then
        assertEquals(var.getName(), found.getName());
    }*/


}
