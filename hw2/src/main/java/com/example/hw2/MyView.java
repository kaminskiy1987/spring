package com.example.hw2;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MyView {
    private static final String HTML_HEAD ="<head> <meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><link rel='stylesheet' href='/style.css'> <title>Document</title> </head>";
    private static final String HTML_START="<html><body> <header><h2>My view application</h2></header>";
    private static final String HTML_END="</body><footer>" + LocalDateTime.now().getYear()  + "</footer></html>";

    public static String draw(){
        StringBuilder page = new StringBuilder();
        page.append(HTML_HEAD);
        page.append(HTML_START);
        //page.append("<h3>Date=" + LocalDateTime.now()  + "</h3>");
        for (MyBook book: MyRepository.getBookList()) {
            page.append("<dev class = 'entry'> <p> id: " + book.getId() + " name: " + book.getName() + "</p>" );
            page.append("<input type='text' class='name_change' value='" + book.getName() + "'>" + "</input>");
            //page.append("<button class='button_delete'>delete</button>");
            page.append("<button class='button_change'>change</button>");
            //page.append("<button class='button_delete' action='#' th:action'@>{/delete}' th:object='${delete}'>delete</button>");
            page.append("<button class='button_delete'>delete</button>");
            page.append("</dev>");
        }

        page.append("<dev class = 'entry'> <p> new book name: </p>" );
        page.append("<input type='text' class='name_change'> </input>");
        page.append("<button class='button_add'>add</button>");
        page.append("</dev>");
        page.append(HTML_END);
        return page.toString();
    }

    public static String delete(){
        MyRepository.removeBook(MyRepository.getBookList().size());
        return draw();
    }

    public static String add(){
        MyRepository.addBook(new MyBook());
        return draw();
    }
}