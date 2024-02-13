package ru.example.hometask_7.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.hometask_7.Entitys.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
