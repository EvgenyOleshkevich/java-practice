/*
package com.example.demo.controllers;

import com.example.demo.CodeResponse;
import com.example.demo.models.NameRequest;
import com.example.demo.models.Response;
import com.example.demo.models.SumRequest;
import com.example.demo.models.Variable;
import com.example.demo.repositories.VariableRepo;
import com.example.demo.repositories.VariableRepoForTesting;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RestAPIControllerTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VariableRepo Vars;

    VariableRepo service = new VariableRepoForTesting();
    @InjectMocks
    RestAPIController controller = new RestAPIController();


    @Test
    void addition() {
        Variable a = new Variable("a", 7);
        controller.setRepo(service);

        Response resp = controller.addByObject(a).getBody();
        assertEquals(1, controller.count());
        assertEquals(CodeResponse.Ok.getCode(), resp.getCode());
        assertEquals(a.getValue(), resp.getValue());

        resp = controller.getByObject(new NameRequest(a.getName())).getBody();
        assertEquals(a.getValue(), resp.getValue());
        assertEquals(CodeResponse.Ok.getCode(), resp.getCode());

        Variable aOther = new Variable("a", 123);
        resp = controller.addByObject(aOther).getBody();
        assertEquals(1, controller.count());
        assertEquals(CodeResponse.VariableAlreadyExist.getCode(), resp.getCode());

        resp = controller.getByObject(new NameRequest(aOther.getName())).getBody();
        assertEquals(a.getValue(), resp.getValue());

        Variable b = new Variable("b", 13);
        resp = controller.addByObject(b).getBody();
        assertEquals(2, controller.count());
        assertEquals(CodeResponse.Ok.getCode(), resp.getCode());
        assertEquals(b.getValue(), resp.getValue());

        resp = controller.getByObject(new NameRequest(b.getName())).getBody();
        assertEquals(b.getValue(), resp.getValue());
        assertEquals(CodeResponse.Ok.getCode(), resp.getCode());

        service.deleteAll();
        assertEquals(0, controller.count());
    }

    @Test
    void gettingNotExisting() {
        controller.setRepo(service);

        NameRequest name = new NameRequest("a");
        Response resp = controller.getByObject(name).getBody();
        assertEquals(CodeResponse.VariableDontExist.getCode(), resp.getCode());
    }

    @Test
    void removing() {
        Variable a = new Variable("a", 7);
        controller.setRepo(service);

        controller.addByObject(a).getBody();
        Response resp = controller.removeByObject(new NameRequest(a.getName())).getBody();
        assertEquals(a.getValue(), resp.getValue());
        assertEquals(CodeResponse.Ok.getCode(), resp.getCode());

        resp = controller.getByObject(new NameRequest(a.getName())).getBody();
        assertEquals(0, resp.getValue());
        assertEquals(CodeResponse.VariableDontExist.getCode(), resp.getCode());

        Variable aOther = new Variable("a", 123);
        resp = controller.addByObject(aOther).getBody();
        resp = controller.getByObject(new NameRequest(aOther.getName())).getBody();
        assertEquals(aOther.getValue(), resp.getValue());
        assertEquals(CodeResponse.Ok.getCode(), resp.getCode());

        // double deleting
        controller.removeByObject(new NameRequest(a.getName())).getBody();
        resp = controller.removeByObject(new NameRequest(a.getName())).getBody();
        assertEquals(0, resp.getValue());
        assertEquals(CodeResponse.VariableDontExist.getCode(), resp.getCode());

        assertEquals(0, controller.count());
    }

    @Test
    void summing() {
        controller.setRepo(service);
        Variable a = new Variable("a", 7);
        controller.addByObject(a).getBody();
        Variable b = new Variable("b", 13);
        controller.addByObject(b).getBody();


        Response resp = controller.sumByObject(new SumRequest(a.getName(), b.getName())).getBody();
        assertEquals(a.getValue() + b.getValue(), resp.getValue());
        assertEquals(CodeResponse.Ok.getCode(), resp.getCode());

        resp = controller.sumByObject(new SumRequest(a.getName(), "c")).getBody();
        assertEquals(0, resp.getValue());
        assertEquals(CodeResponse.VariableDontExist.getCode(), resp.getCode());

        service.deleteAll();
        assertEquals(0, controller.count());
    }
}
*/
