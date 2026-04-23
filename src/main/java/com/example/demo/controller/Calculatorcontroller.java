package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.CalculatorService;

@Controller
public class Calculatorcontroller {

    @Autowired
    private CalculatorService calculatorService;

    // Home page
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    // Handle calculation
    @PostMapping("/calculate")
    public String calculate(@RequestParam("num1") double n1,
                            @RequestParam("num2") double n2,
                            @RequestParam("operation") String operation,
                            Model model) {

        double result = 0;

        try {
            switch (operation) {
                case "add":
                    result = calculatorService.add(n1, n2);
                    break;
                case "sub":
                    result = calculatorService.subtract(n1, n2);
                    break;
                case "mul":
                    result = calculatorService.multiply(n1, n2);
                    break;
                case "div":
                    result = calculatorService.divide(n1, n2);
                    break;
                default:
                    model.addAttribute("error", "Invalid operation");
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("num1", n1);
        model.addAttribute("num2", n2);
        model.addAttribute("operation", operation);
        model.addAttribute("result", result);

        return "result";
    }
}