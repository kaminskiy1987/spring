package ru.example.hometask_8.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.hometask_8.Entitys.Task;
import ru.example.hometask_8.Entitys.TaskStatus;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByStatus(TaskStatus status);
}
