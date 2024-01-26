package ru.example.DataProcessingService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataProcessingRepository extends JpaRepository<User,Long> {

}