package ru.example.hometask_6.Controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.hometask_6.Entitys.Employer;
import ru.example.hometask_6.Entitys.Task;
import ru.example.hometask_6.Services.EmployerService;
import ru.example.hometask_6.Services.TaskService;
import ru.example.hometask_6.Entitys.TaskStatus;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskApiController {
    private final TaskService taskService;
    private final EmployerService employerService;

    public TaskService getTaskService() {
        return taskService;
    }

    @Autowired
    public TaskApiController(TaskService service, EmployerService employerService) {
        this.taskService = service;
        this.employerService = employerService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskService.get();
    }

    @GetMapping(path = "/tasks/{id}")
    public Task getTaskById(@RequestParam("id") Long id){
        return taskService.getById(id);
    }

    @PostMapping(path = "/tasks/create/{description}")
    public Task createTask(@RequestParam("description") String description){
        return taskService.create(description);
    }

    @DeleteMapping(path = "/tasks/delete/{id}")
    public Task deleteTaskById(@RequestParam("id") Long id){
        return taskService.deleteById(id);
    }

    @PutMapping(path = "/tasks/update/{id}/{description}")
    public Task updateTaskById(@RequestParam("id") Long id, @RequestParam("description") String description){
        return taskService.updateById(description,id);
    }

    @PutMapping(path = "/tasks/status/{id}/{status}")
    public Task updateTaskStatus(@RequestParam("id") Long id, @RequestParam("status") String status){
        return taskService.setStatus(TaskStatus.convert(status),id);
    }

    @GetMapping(path = "/tasks/select/{status}")
    public List<Task> selectTaskByStatus(@RequestParam("status") TaskStatus status){
        return taskService.getByStatus(status);
    }

    @GetMapping("/employees")
    public List<Employer> getEmployees(){
        return employerService.get();
    }

    @GetMapping(path = "/employees/{id}")
    public Employer getEmployerById(@RequestParam("id") Long id){
        return employerService.getById(id);
    }

    @PostMapping(path = "/employees/create/{name}")
    public Employer createEmployer(@RequestParam("name") String name){
        return employerService.create(name);
    }

    @DeleteMapping(path = "/employees/delete/{id}")
    public Employer deleteEmployerById(@RequestParam("id") Long id){
        return employerService.deleteById(id);
    }
    @PutMapping(path = "/employees/update/{id}/{name}")
    public Employer updateEmployerById(@RequestParam("id") Long id, @RequestParam("name") String name){
        return employerService.updateById(name,id);
    }

    @PutMapping(path = "/assignTasks/{employerId}/{tasksId}")
    public Employer updateEmployerTask(@RequestParam("employerId") Long id, @RequestParam("tasksId") List<Long> tasksId){
        Employer temp = employerService.getById(id);
        if (temp != null){
            List<Task> validTasks = tasksId.stream().filter(taskId ->(taskService.getById(taskId)) != null)
                    .map(i -> (taskService.getById(i))).toList();
            employerService.updateById(validTasks, id);
        }
        return temp;
    }

    @PutMapping(path = "/addTask/{employerId}/{tasksId}")
    public Employer addEmployerTask(@RequestParam("employerId") Long id, @RequestParam("tasksId") Long taskId){
        Employer temp = employerService.getById(id);
        if (temp != null){
            List<Task> employerTasks = temp.getTasks();
            if (taskService.get().contains(taskService.getById(taskId))) {
                if (! employerTasks.contains(taskService.getById(taskId))){
                    employerTasks.add(taskService.getById(taskId));
                }
            }
            employerService.updateById(employerTasks, id);
        }
        return temp;
    }
    
    @PutMapping(path = "/removeTask/{employerId}/{tasksId}")
    public Employer removeEmployerTask(@RequestParam("employerId") Long id, @RequestParam("tasksId") Long taskId){
        Employer temp = employerService.getById(id);
        if (temp != null){
            List<Task> employerTasks = temp.getTasks();
            if (taskService.get().contains(taskService.getById(taskId))) {
                if (employerTasks.contains(taskService.getById(taskId))){
                    employerTasks.remove(taskService.getById(taskId));
                }
            }
            employerService.updateById(employerTasks, id);
        }
        return temp;
    }
}
