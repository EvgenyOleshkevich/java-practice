package com.example.demo.controllers;

import com.example.demo.models.NameRequest;
import com.example.demo.models.Variable;
import com.example.demo.repositories.VariableRepo;
import com.example.demo.repositories.VariableRepoForTesting;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RestAPIControllerTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VariableRepo Vars;

    @Mock
    VariableRepo mockService = new VariableRepoForTesting();
    @InjectMocks
    RestAPIController controller = new RestAPIController();


    @Test
    void test()
            throws Exception {
        Variable var = new Variable("a", 7);
        controller.setRepo(mockService);

        controller.addByObject(var);
        assertEquals(var.getValue(), controller.getByObject(new NameRequest(var.getName())).getBody().getValue());
    }
}
