package ru.example.DataProcessingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final DataProcessingService dataProcessingService;

    @Autowired
    public RegistrationService(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    public User registrateUser(String name, int age, String email){
        return dataProcessingService.createUser(name, age, email);
    }
}