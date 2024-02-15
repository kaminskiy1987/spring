package ru.example.hometask_8.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column(nullable = false)
    private TaskStatus status;

    @Column(nullable = false)
    private Date dateBegin;

    @Column(nullable = true)
    private Date dateEnd;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Employer employer;

    @Autowired
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.TASK_NEW;
        this.dateBegin = new Date();
        this.dateEnd = null;
    }

    @Autowired
    public Task(){
        this.description = "";
        this.status = TaskStatus.TASK_NEW;
        this.dateBegin = new Date();
        this.dateEnd = null;
    }
    
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", employer=" + employer +
                '}';
    }
}
