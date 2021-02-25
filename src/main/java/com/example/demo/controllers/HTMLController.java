/*
package com.example.demo.controllers;
import com.example.demo.repositories.VariableRepo;
import com.example.demo.models.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HTMLController {
    @Autowired
    private VariableRepo vars;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("response", "main");
        return "home";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        return "add";
    }

    @GetMapping("/get")
    public String getGettingPage(Model model) {
        return "get";
    }

    @GetMapping("/remove")
    public String getRemovePage(Model model) {
        return "remove";
    }

    @GetMapping("/sum")
    public String sum(Model model) {
        return "sum";
    }



    @PostMapping("/add")
    public String add(@RequestParam(name="name", required=true) String name,
                      @RequestParam(name="value", required=true) int value,
                      Model model) {

        if (!vars.findById(name).isPresent()) {
            Variable variable = new Variable();
            variable.setName(name);
            variable.setValue(value);
            vars.save(variable);
        } else {
            model.addAttribute("response",
                    "variable already exist");
        }

        return "home";
    }

    @PostMapping("/get")
    public String get(@RequestParam(name="name", required=true) String name,
                         Model model) {
        Optional<Variable> optVar = vars.findById(name);
        if (optVar.isPresent()) {
            model.addAttribute("response", optVar.get().getValue());
        } else {
            model.addAttribute("response",
                    "variable doesnt exist");
        }
        return "home";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam(name="name", required=true) String name,
                          Model model) {

        Optional<Variable> optVar = vars.findById(name);
        if (optVar.isPresent())
        {
            vars.delete(optVar.get());
        }
        return "home";
    }

    @PostMapping("/sum")
    public String sum(@RequestParam(name="first", required=true) String first,
                      @RequestParam(name="second", required=true) String second,
                      Model model) {

        Optional<Variable> optFirst = vars.findById(first);
        Optional<Variable> optSecond = vars.findById(second);
        if (optFirst.isPresent() && optSecond.isPresent()) {
            model.addAttribute("response",
                    optFirst.get().getValue() + optSecond.get().getValue());
        } else {
            model.addAttribute("response",
                    "one or two variable dont exist");
        }
        return "home";
    }
}

*/
