package ru.example.hometask_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/tasks")
public class TaskWebController {

    private final TaskService service;
    @Autowired
    public TaskWebController(TaskService service) {
        this.service = service;
    }

    @RequestMapping()
    public String tasksPage(Model model) {
        model.addAttribute("tasks", service.getTasks());
        return "tasks";
    }
}
