package ru.example.Hometask_4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataProcessingRepository extends JpaRepository<User,Long> {
}