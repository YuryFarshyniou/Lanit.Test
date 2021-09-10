package ru.lanit.test.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(value = "main")
    public String getMainPage() {
        return "mainPage";
    }
}
