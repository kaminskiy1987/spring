package ru.example.hometask_5;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//1. Добавление задачи.
//2. Просмотр всех задач.
//3. Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").
//4. Изменение статуса задачи.
//5. Удаление задачи.
@Service
public class TaskService {
    private final TaskRepository repository;
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> createTask(String description){
        Optional<Task> temp = Optional.of(new Task(description));
        repository.save(temp.get());
        System.out.println("createTask: " + temp.toString());
        return temp;
    }

    public List<Task> getTasks(){
        List<Task> temp = repository.findAll();
        temp.forEach(t -> System.out.println("getTasks : " + t.toString()));
        return temp;
    }

    public Optional<Task> getTaskById(Long id){
        Optional<Task> temp = Optional.of(repository.getById(id));
        System.out.println("getTaskById: " + temp.toString());
        return temp;
    }

    public Optional<Task> deleteTaskById(Long id){
        Optional<Task> temp = Optional.of(repository.getById(id));
        if (temp != null) {
            repository.delete(temp.get());
        }
        System.out.println("deleteTaskById: " + temp.toString());
        return temp;
    }

    public Optional<Task> updateTaskById(String description, Long id){
        Optional<Task> temp = Optional.of(repository.getById(id));
        if (temp != null) {
            temp.get().setDescription(description);
            repository.save(temp.get());
        }
        System.out.println("updateTaskById: " + temp.toString());
        return temp;
    }

    public Optional<Task> setTaskStatus(TaskStatus status, Long id) {
        Optional<Task> temp = Optional.of(repository.getById(id));
        if (temp != null) {
            temp.get().setStatus(status);
            if (status.equals(TaskStatus.TASK_NEW)){
                temp.get().setDateBegin(new Date());
                temp.get().setDateEnd(null);
            }

            if (status.equals(TaskStatus.TASK_COMPETED)){
                temp.get().setDateEnd(new Date());
            }

            repository.save(temp.get());
        }
        System.out.println("setTaskStatus: " + temp.toString());
        return temp;
    }
    
    public List<Task> getTasksByStatus(TaskStatus taskStatus){
        List<Task> temp = new ArrayList<>();
        temp = repository.findByStatus(taskStatus);
        temp.forEach(t -> System.out.println("getTasks : " + t.toString()));
        return temp;
    }

}
