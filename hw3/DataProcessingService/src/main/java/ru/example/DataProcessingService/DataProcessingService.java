package ru.example.DataProcessingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataProcessingService {
    private final DataProcessingRepository dataProcessingRepository;
    private final NotifyService notifyService;

    @Autowired
    public DataProcessingService(DataProcessingRepository dataProcessingRepository, NotifyService notifyService) {
        this.dataProcessingRepository = dataProcessingRepository;
        this.notifyService = notifyService;
    }

    public List<User> getUsers() {
        return dataProcessingRepository.findAll();
    }

    public User createUser(String name, int age, String email){
        boolean containsFlag = false;
        User user = new User(name, age, email);
        containsFlag = getUsers().stream().filter(t -> t.equals(user)).count() >= 1;
        if (!containsFlag) {
            dataProcessingRepository.save(user);
            notifyService.notify(user.toString() + " was created");
        } else {
            notifyService.notify(user.toString() + " already in base");
        }
        return user;
    }

    public void setUsers(List<User> users) {
        dataProcessingRepository.saveAll(users);
    }

    public List<User> sortByName(){
        notifyService.notify("base was sorted by name");
        List<User> temp = getUsers().stream().sorted().toList();
        temp.stream().forEach(t ->notifyService.notify(t.toString()));
        return temp;
    }

    public List<User> filterByAge(int age){
        notifyService.notify("filtered by age = " + age);
        List<User> temp = getUsers().stream().filter(t -> t.getAge() > age).toList();
        temp.stream().forEach(t ->notifyService.notify(t.toString()));
        return temp;
    }

    public int calculateAverageAge(){
        int temp = (int) getUsers().stream().mapToInt(User::getAge).average().orElse(0.0);
        notifyService.notify("average age in base = " + temp);
        return temp;
    }
}
