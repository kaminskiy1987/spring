package ru.example.DataProcessingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userApi")
public class DataProcessingController {
    private final DataProcessingService dataProcessingService;

    @Autowired
    public DataProcessingController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> homeUsers() {
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public ResponseEntity<List<User>> sortUsers() {
        return new ResponseEntity<>(dataProcessingService.sortByName(), HttpStatus.OK);
    }

    @RequestMapping(value = "/filter/{age}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> filterUsers(@PathVariable("age") int age) {
        return new ResponseEntity<>(dataProcessingService.filterByAge(age), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<List<User>> createUser(@RequestBody User user) {
        dataProcessingService.createUser(user.getName(), user.getAge(), user.getEmail());
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create/{name}/{age}/{email}", method = RequestMethod.POST)
    public ResponseEntity<List<User>> createUserParam(@PathVariable("name") String name, @PathVariable("age") int age, @PathVariable("email") String email) {
        dataProcessingService.createUser(name, age, email);
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/average", method = RequestMethod.GET)
    public ResponseEntity<Integer> averageAge() {
        return new ResponseEntity<>(dataProcessingService.calculateAverageAge(), HttpStatus.OK);
    }


    @RequestMapping(value = "/register/{name}-{age}-{email}", method = RequestMethod.POST)
    public ResponseEntity<List<User>> registerNewUser(@PathVariable("name") String name, @PathVariable("age") int age, @PathVariable("email") String email) {
        dataProcessingService.createUser(name, age, email);
        dataProcessingService.sortByName();
        dataProcessingService.calculateAverageAge();
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.OK);
    }
}
