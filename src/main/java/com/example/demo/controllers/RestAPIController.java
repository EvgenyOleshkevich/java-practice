package com.example.demo.controllers;


import com.example.demo.CodeResponse;
import com.example.demo.models.NameRequest;
import com.example.demo.models.Response;
import com.example.demo.models.SumRequest;
import com.example.demo.models.Variable;
import com.example.demo.repositories.VariableRepo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/variables")
public class RestAPIController {

    @Autowired
    private VariableRepo vars;

    public void setRepo(VariableRepo vars) {
        this.vars = vars;
    }
    public long count() {
        return vars.count();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getByPageParam(@PathVariable("id") String name) {
        Optional<Variable> var = vars.findById(name);
        if (var.isPresent()) {
            return ResponseEntity.ok(new Response(var.get().getValue(),
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist));
        }
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<Response> getByObject(@RequestBody  NameRequest name) {

        Optional<Variable> var = vars.findById(name.getName());
        if (var.isPresent()) {
            return ResponseEntity.ok(new Response(var.get().getValue(),
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist));
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
                        CodeResponse.JSONFormatError));
            }
        }
        catch (JSONException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.InvalidRequest));
        }

        Optional<Variable> var = vars.findById(name);
        if (var.isPresent()) {
            return ResponseEntity.ok(new Response(var.get().getValue(),
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist));
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
                    CodeResponse.VariableDontExist));
        }
    }

    @GetMapping("/sum")
    @ResponseBody
    public ResponseEntity<Response> sumByObject(@RequestBody SumRequest req) {
        Optional<Variable> var1 = vars.findById(req.getFirst());
        Optional<Variable> var2 = vars.findById(req.getSecond());

        if (var1.isPresent() && var2.isPresent()) {
            return ResponseEntity.ok(new Response(var1.get().getValue() + var2.get().getValue(),
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist));
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
                        CodeResponse.JSONFormatError));
            }
        }
        catch (JSONException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.InvalidRequest));
        }
        Optional<Variable> var1 = vars.findById(name1);
        Optional<Variable> var2 = vars.findById(name2);

        if (var1.isPresent() && var2.isPresent()) {
            return ResponseEntity.ok(new Response(var1.get().getValue() + var2.get().getValue(),
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableDontExist));
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Response> addByObject(@RequestBody Variable var) {
        if (!vars.findById(var.getName()).isPresent()) {
            Variable createdVar = vars.save(var);
            if (createdVar == null) {
                return ResponseEntity.ok(new Response(0,
                        CodeResponse.CouldNotSaveObject));
            } else {
                /*URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdVar.getName())
                        .toUri();

                return ResponseEntity.created(uri)
                        .body(new Response(createdVar.getValue(),
                                CodeResponse.Ok));*/
                return ResponseEntity.ok(new Response(createdVar.getValue(),
                        CodeResponse.Ok));
            }
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.VariableAlreadyExist));
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
                        CodeResponse.JSONFormatError));
            }
        }
        catch (JSONException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.InvalidRequest));
        }
        try {
            value = Integer.parseInt(str_value);
        } catch (NumberFormatException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.JSONFormatError));
        }

        if (!vars.findById(name).isPresent()) {
            Variable createdVar = new Variable(name, value);
            vars.save(createdVar);
            return ResponseEntity.ok(new Response(createdVar.getValue(),
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.VariableAlreadyExist));
        }
    }

    @GetMapping("/remove/{id}")
    public ResponseEntity<Response> removeByPageParam(@PathVariable("id") String name) {
        Optional<Variable> var = vars.findById(name);
        if (var.isPresent())
        {
            vars.delete(var.get());
            return ResponseEntity.ok(new Response(var.get().getValue(),
                    CodeResponse.Ok));
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.VariableDontExist));
    }

    @DeleteMapping("/remove")
    @ResponseBody
    public ResponseEntity<Response> removeByObject(@RequestBody NameRequest name) {
        Optional<Variable> var = vars.findById(name.getName());
        if (var.isPresent())
        {
            vars.delete(var.get());
            return ResponseEntity.ok(new Response(var.get().getValue(),
                CodeResponse.Ok));
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.VariableDontExist));
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
                        CodeResponse.JSONFormatError));
            }
        }
        catch (JSONException e) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.InvalidRequest));
        }

        Optional<Variable> var = vars.findById(name);
        if (var.isPresent())
        {
            vars.delete(var.get());
            return ResponseEntity.ok(new Response(var.get().getValue(),
                    CodeResponse.Ok));
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.VariableDontExist));
    }
}
