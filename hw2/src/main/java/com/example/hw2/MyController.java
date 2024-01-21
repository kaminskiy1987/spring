package com.example.hw2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/")
    public String home() {
        return MyView.draw();
    }

    @GetMapping("/delete")
    public String delete() {
        return MyView.delete();
    }

    @GetMapping("/add")
    public String add() {
        return MyView.add();
    }
}
