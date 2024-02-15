package ru.example.hometask_8.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.hometask_8.Annotations.Log;
import ru.example.hometask_8.Annotations.Performance;
import ru.example.hometask_8.Entitys.Employer;
import ru.example.hometask_8.Entitys.Task;
import ru.example.hometask_8.Repositories.EmployerRepository;

import java.util.List;

@Service
public class EmployerService {
    private final EmployerRepository repository;

    @Autowired
    public EmployerService(EmployerRepository repository) {
        this.repository = repository;
    }

    @Log
    @Performance
    public Employer create(String name){
        Employer temp = new Employer(name);
        repository.save(temp);

        return temp;
    }

    @Log
    @Performance
    public List<Employer> get(){
        List<Employer> temp = repository.findAll();

        return temp;
    }

    @Log
    @Performance
    public Employer getById(Long id){
        Employer temp = repository.findById(id).orElse(null);

        return temp;
    }

    @Log
    @Performance
    public Employer deleteById(Long id){
        Employer temp = repository.findById(id).orElse(null);

        if (temp != null) {
            repository.delete(temp);
        }

        return temp;
    }

    @Log
    @Performance
    public Employer updateById(String name, Long id){
        Employer temp = repository.findById(id).orElse(null);

        if (temp != null) {
            temp.setName(name);
            repository.save(temp);
        }

        return temp;
    }

    @Log
    @Performance
    public Employer updateById(List<Task> tasks, Long id) {
        Employer temp = repository.findById(id).orElse(null);
        
        if (temp != null) {
            temp.setTasks(tasks);
            repository.delete(temp);
            repository.save(temp);
        }

        return temp;
    }
}