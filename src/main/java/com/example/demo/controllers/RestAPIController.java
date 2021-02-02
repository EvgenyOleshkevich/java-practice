package com.example.demo.controllers;


import com.example.demo.CodeResponse;
import com.example.demo.repositories.VariableRepo;
import com.example.demo.models.NameRequest;
import com.example.demo.models.Response;
import com.example.demo.models.SumRequest;
import com.example.demo.models.Variable;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/variables")
public class RestAPIController {

    @Autowired
    private VariableRepo vars;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getByPageParam(@PathVariable("id") String name) {
        Optional<Variable> var = vars.findById(name);
        if (var.isPresent()) {
            return ResponseEntity.ok(new Response(var.get().getValue(),
                    CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist.getCode(), CodeResponse.VariableDontExist.toString()));
        }
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<Response> getByObject(@RequestBody  NameRequest name) {

        Optional<Variable> var = vars.findById(name.getName());
        if (var.isPresent()) {
            return ResponseEntity.ok(new Response(var.get().getValue(),
                    CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist.getCode(), CodeResponse.VariableDontExist.toString()));
        }
    }

    @GetMapping("/getByJSON")
    public ResponseEntity<Response> getByJSONString(@RequestParam  String strJSON) {
        String name;
        try {
            JSONObject req = new JSONObject(strJSON);
            try {
                name = req.get("name").toString();
            }catch (JSONException e) {
                return ResponseEntity.ok(new Response(0,
                        CodeResponse.JSONFormatError.getCode(), CodeResponse.JSONFormatError.toString()));
            }
        }
        catch (JSONException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.InvalidRequest.getCode(), CodeResponse.InvalidRequest.toString()));
        }

        Optional<Variable> var = vars.findById(name);
        if (var.isPresent()) {
            return ResponseEntity.ok(new Response(var.get().getValue(),
                    CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist.getCode(), CodeResponse.VariableDontExist.toString()));
        }
    }

    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<Response> sumByPageParam(@PathVariable("id1") String name1,
                                        @PathVariable("id2") String name2) {
        Optional<Variable> var1 = vars.findById(name1);
        Optional<Variable> var2 = vars.findById(name2);

        if (var1.isPresent() && var2.isPresent()) {
            return ResponseEntity.ok(new
                    Response(var1.get().getValue() + var2.get().getValue(),
                    CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist.getCode(), CodeResponse.VariableDontExist.toString()));
        }
    }

    @GetMapping("/sum")
    @ResponseBody
    public ResponseEntity<Response> sumByObject(@RequestBody SumRequest req) {
        Optional<Variable> var1 = vars.findById(req.getFirst());
        Optional<Variable> var2 = vars.findById(req.getSecond());

        if (var1.isPresent() && var2.isPresent()) {
            return ResponseEntity.ok(new Response(var1.get().getValue() + var2.get().getValue(),
                    CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist.getCode(), CodeResponse.VariableDontExist.toString()));
        }
    }

    @GetMapping("/sumByJSON")
    public ResponseEntity<Response> sumByJSONString(@RequestParam  String strJSON) {
        String name1;
        String name2;
        try {
            JSONObject req = new JSONObject(strJSON);
            try {
                name1 = req.get("first").toString();
                name2 = req.get("second").toString();
            }catch (JSONException e) {
                return ResponseEntity.ok(new Response(0,
                        CodeResponse.JSONFormatError.getCode(), CodeResponse.JSONFormatError.toString()));
            }
        }
        catch (JSONException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.InvalidRequest.getCode(), CodeResponse.InvalidRequest.toString()));
        }
        Optional<Variable> var1 = vars.findById(name1);
        Optional<Variable> var2 = vars.findById(name2);

        if (var1.isPresent() && var2.isPresent()) {
            return ResponseEntity.ok(new Response(var1.get().getValue() + var2.get().getValue(),
                    CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist.getCode(), CodeResponse.VariableDontExist.toString()));
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Response> addByObject(@RequestBody Variable var)
            throws URISyntaxException {
        if (!vars.findById(var.getName()).isPresent()) {
            Variable createdVar = vars.save(var);
            if (createdVar == null) {
                return ResponseEntity.notFound().build();
            } else {
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdVar.getName())
                        .toUri();

                return ResponseEntity.created(uri)
                        .body(new Response(createdVar.getValue(),
                                CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
            }
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.VariableAlreadyExist.getCode(), CodeResponse.VariableAlreadyExist.toString()));
    }

    @PostMapping("/addByJSON")
    public ResponseEntity<Response> addByJSONString(@RequestParam  String strJSON) {
        String name;
        String str_value;
        int value;
        try {
            JSONObject req = new JSONObject(strJSON);
            try {
                name = req.get("name").toString();
                str_value = req.get("value").toString();
            }catch (JSONException e) {
                return ResponseEntity.ok(new Response(0,
                        CodeResponse.JSONFormatError.getCode(), CodeResponse.JSONFormatError.toString()));
            }
        }
        catch (JSONException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.InvalidRequest.getCode(), CodeResponse.InvalidRequest.toString()));
        }
        try {
            value = Integer.parseInt(str_value);
        } catch (NumberFormatException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.JSONFormatError.getCode(), CodeResponse.JSONFormatError.toString()));
        }

        if (!vars.findById(name).isPresent()) {
            Variable createdVar = new Variable(name, value);
            vars.save(createdVar);
            return ResponseEntity.ok(new Response(createdVar.getValue(),
                    CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableAlreadyExist.getCode(), CodeResponse.VariableAlreadyExist.toString()));
        }
    }

    @GetMapping("/remove/{id}")
    public ResponseEntity<Response> removeByPageParam(@PathVariable("id") String name) {
        Optional<Variable> var = vars.findById(name);
        if (var.isPresent())
        {
            vars.delete(var.get());
        }
        return ResponseEntity.ok(new Response(var.get().getValue(), 0, "OK"));
    }

    @DeleteMapping("/remove")
    @ResponseBody
    public ResponseEntity<Response> removeByObject(@RequestBody NameRequest name) {
        Optional<Variable> var = vars.findById(name.getName());
        if (var.isPresent())
        {
            vars.delete(var.get());
        }
        return ResponseEntity.ok(new Response(var.get().getValue(),
                CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
    }

    @DeleteMapping("/removeByJSON")
    public ResponseEntity<Response> removeByJSONString(@RequestParam String strJSON) {
        String name;
        try {
            JSONObject req = new JSONObject(strJSON);
            try {
                name = req.get("name").toString();
            }catch (JSONException e) {
                return ResponseEntity.ok(new Response(0,
                        CodeResponse.JSONFormatError.getCode(), CodeResponse.JSONFormatError.toString()));
            }
        }
        catch (JSONException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.InvalidRequest.getCode(), CodeResponse.InvalidRequest.toString()));
        }

        Optional<Variable> var = vars.findById(name);
        if (var.isPresent())
        {
            vars.delete(var.get());
        }
        return ResponseEntity.ok(new Response(var.get().getValue(),
                CodeResponse.Ok.getCode(), CodeResponse.Ok.toString()));
    }
}
