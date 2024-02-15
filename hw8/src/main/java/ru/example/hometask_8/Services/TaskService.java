package ru.example.hometask_8.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.hometask_8.Annotations.Log;
import ru.example.hometask_8.Annotations.Performance;
import ru.example.hometask_8.Entitys.Employer;
import ru.example.hometask_8.Entitys.Task;
import ru.example.hometask_8.Repositories.TaskRepository;
import ru.example.hometask_8.Entitys.TaskStatus;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Log
    @Performance
    public Task create(String description){
        Task temp = (new Task(description));
        repository.save(temp);

        return temp;
    }

    @Log
    @Performance
    public List<Task> get(){
        List<Task> temp = repository.findAll();

        return temp;
    }

    @Log
    @Performance
    public Task getById(Long id){
        Task temp = repository.findById(id).orElse(null);

        return temp;
    }

    @Log
    @Performance
    public Task deleteById(Long id){
        Task temp = repository.findById(id).orElse(null);

        if (temp != null) {
            repository.delete(temp);
        }

        return temp;
    }

    @Log
    @Performance
    public Task updateById(String description, Long id){
        Task temp = repository.findById(id).orElse(null);

        if (temp != null) {
            temp.setDescription(description);
            repository.save(temp);
        }

        return temp;
    }

    @Log
    @Performance
    public Task updateById(Employer employer, Long id) {
        Task temp = repository.findById(id).orElse(null);

        if (temp != null) {
            temp.setEmployer(employer);
            repository.save(temp);
        }

        return temp;
    }

    @Log
    @Performance
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
        }

        return temp;
    }

    @Log
    @Performance
    public List<Task> getByStatus(TaskStatus taskStatus){
        List<Task> temp = repository.findByStatus(taskStatus);
        
        return temp;
    }

}
