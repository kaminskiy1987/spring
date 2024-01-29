package ru.example.Hometask_4;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class DataProcessingController {
    private final DataProcessingService dataProcessingService;

    @Autowired
    public DataProcessingController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    @RequestMapping(value = "")
    public String homeUsers() {
        return "forward:index.html";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", dataProcessingService.getUsers());
        return "list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("id") long id, Model model) {
        System.out.println("Delete was active " + id);
        dataProcessingService.deleteUser(id);
        model.addAttribute("users", dataProcessingService.getUsers());
        return "list";
    }
}