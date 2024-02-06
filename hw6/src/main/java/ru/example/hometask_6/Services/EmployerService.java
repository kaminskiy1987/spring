package ru.example.hometask_6.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.hometask_6.Entitys.Employer;
import ru.example.hometask_6.Entitys.Task;
import ru.example.hometask_6.Entitys.TaskStatus;
import ru.example.hometask_6.Repositories.EmployerRepository;
import ru.example.hometask_6.Repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    private final EmployerRepository repository;

    @Autowired
    public EmployerService(EmployerRepository repository) {
        this.repository = repository;
    }

    public Employer create(String name){
        Employer temp = new Employer(name);
        repository.save(temp);
        System.out.println("create Employer: " + temp);
        return temp;
    }

    public List<Employer> get(){
        List<Employer> temp = repository.findAll();
        temp.forEach(t -> System.out.println("get all Employers : " + t));
        return temp;
    }

    public Employer getById(Long id){
        Employer temp = repository.findById(id).orElse(null);
        if (temp != null){
            System.out.println("get Employer: " + temp);
        } else {
            System.out.println("get Employer: " + id + " not found in base");
        }
        return temp;
    }

    public Employer deleteById(Long id){
        Employer temp = repository.findById(id).orElse(null);
        if (temp != null) {
            repository.delete(temp);
            System.out.println("delete Employer: " + temp);
        } else {
            System.out.println("delete Employer: " + id + " not found in base");
        }
        return temp;
    }

    public Employer updateById(String name, Long id){
        Employer temp = repository.findById(id).orElse(null);
        if (temp != null) {
            temp.setName(name);
            repository.save(temp);
            System.out.println("update Employer data: " + temp);
        }else {
            System.out.println("update Employer data: " + id + " not found in base");
        }
        return temp;
    }

    public Employer updateById(List<Task> tasks, Long id) {
        Employer temp = repository.findById(id).orElse(null);
        if (temp != null) {
            temp.setTasks(tasks);
            repository.delete(temp);
            repository.save(temp);
            System.out.println("update Employer tasks: " + temp);
        } else {
            System.out.println("update Employer tasks: " + id + " not found in base");
        }
        return temp;
    }
}