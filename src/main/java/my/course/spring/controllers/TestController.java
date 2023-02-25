package my.course.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(@RequestParam(value = "name", required = false, defaultValue = "Petya") String name, Model model) {
        model.addAttribute("message", "Hello dear " + name);
        System.out.println("Hello " + name);
        return "test";
    }
}
