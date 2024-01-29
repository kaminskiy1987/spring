package ru.example.Hometask_4;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotifyService {
    public void notify(String msg){
        System.out.println((new Date()).toString() + " : " + msg);
    }
}