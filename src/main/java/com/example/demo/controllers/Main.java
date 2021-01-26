package com.example.demo.controllers;

import com.example.demo.TaskApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("response", "main");
        return "home";
    }


    @GetMapping("/add")
    public String add(@RequestParam(name="name", required=true/*, defaultValue="a"*/) String name,
                      @RequestParam(name="value", required=true) int value,
                      Model model) {
        String request = "add {\"name\":\"" + name + "\",\"value\":" + value + "}";
        model.addAttribute("response", TaskApplication.request(request).toString());
        return "home";
    }
    @GetMapping("/remove")
    public String remove(@RequestParam(name="name", required=true) String name,
                         Model model) {
        String request = "remove {\"name\":\"" + name + "\"}";
        model.addAttribute("response", TaskApplication.request(request).toString());
        return "home";
    }

    @GetMapping("/get")
    public String get(@RequestParam(name="name", required=true) String name,
                         Model model) {
        String request = "get {\"name\":\"" + name + "\"}";
        model.addAttribute("response", TaskApplication.request(request).toString());
        return "home";
    }

    @GetMapping("/sum")
    public String sum(@RequestParam(name="first", required=true) String first,
                      @RequestParam(name="second", required=true) String second,
                      Model model) {
        String request = "sum {\"first\":\"" + first + "\",\"second\":\"" + second + "\"}";
        model.addAttribute("response", TaskApplication.request(request).toString());
        return "home";
    }

}

