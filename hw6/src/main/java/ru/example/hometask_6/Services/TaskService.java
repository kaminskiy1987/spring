package ru.example.hometask_6.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.hometask_6.Entitys.Employer;
import ru.example.hometask_6.Entitys.Task;
import ru.example.hometask_6.Repositories.TaskRepository;
import ru.example.hometask_6.Entitys.TaskStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String description){
        Task temp = (new Task(description));
        repository.save(temp);
        System.out.println("create Task: " + temp);
        return temp;
    }

    public List<Task> get(){
        List<Task> temp = repository.findAll();
        temp.forEach(t -> System.out.println("get all Tasks" + t));
        return temp;
    }

    public Task getById(Long id){
        Task temp = repository.findById(id).orElse(null);
        if (temp != null){
            System.out.println("get Task: " + temp);
        } else {
            System.out.println("get Task: " + id + " not found in base");
        }
        return temp;
    }

    public Task deleteById(Long id){
        Task temp = repository.findById(id).orElse(null);
        if (temp != null) {
            repository.delete(temp);
            System.out.println("delete Employer: " + temp);
        } else {
            System.out.println("delete Employer: " + id + " not found in base");
        }
        return temp;
    }

    public Task updateById(String description, Long id){
        Task temp = repository.findById(id).orElse(null);
        if (temp != null) {
            temp.setDescription(description);
            repository.save(temp);
            System.out.println("update Task: " + temp);
        }else {
            System.out.println("update Task: " + id + " not found in base");
        }
        return temp;
    }

    public Task updateById(Employer employer, Long id) {
        Task temp = repository.findById(id).orElse(null);
        if (temp != null) {
            temp.setEmployer(employer);
            repository.save(temp);
            System.out.println("update Task: " + temp);
        } else {
            System.out.println("update Task: " + id + " not found in base");
        }
        return temp;
    }

    public Task setStatus(TaskStatus status, Long id) {
        Task temp = repository.findById(id).orElse(null);
        if (temp != null) {
            temp.setStatus(status);
            if (status.equals(TaskStatus.TASK_NEW)){
                temp.setDateBegin(new Date());
                temp.setDateEnd(null);
            }

            if (status.equals(TaskStatus.TASK_COMPETED)){
                temp.setDateEnd(new Date());
            }
            repository.save(temp);
            System.out.println("update Task status: " + temp);
        }
        else {
            System.out.println("update Task: " + id + " not found in base");
        }
        return temp;
    }
    public List<Task> getByStatus(TaskStatus taskStatus){
        List<Task> temp = repository.findByStatus(taskStatus);
        temp.forEach(t -> System.out.println("get all Tasks by status" + taskStatus + " : " + t));
        return temp;
    }
}