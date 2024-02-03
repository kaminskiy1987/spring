package ru.example.hometask_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskApiController {
    private final TaskService service;

    public TaskService getService() {
        return service;
    }

    @Autowired
    public TaskApiController(TaskService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Task> getTasks(){
        return service.getTasks();
    }

    @GetMapping(path = "/{id}")
    public Optional<Task> getTasksById(@PathVariable("id") Long id){
        Optional<Task> temp = service.getTaskById(id);
        return temp;
    }

    @PostMapping(path = "/create/{description}")
    public Optional<Task> createTask(@PathVariable("description") String description){
        return service.createTask(description);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Optional<Task> deleteTask(@PathVariable("id") Long id){
        return service.deleteTaskById(id);
    }
    @PutMapping(path = "/update/task/{id}/{description}")
    public Optional<Task> updateTaskById(@PathVariable("id") Long id, @PathVariable("description") String description){
        return service.updateTaskById(description,id);
    }
    @PutMapping(path = "/update/status/{id}/{status}")
    public Optional<Task> updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
        return service.setTaskStatus(TaskStatus.convert(status),id);
    }
    @GetMapping(path = "/select/{status}")
    public List<Task> selectByStatus(@PathVariable("status") String status){
        return service.getTasksByStatus(TaskStatus.convert(status));
    }
}