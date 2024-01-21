package com.example.hw2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Scope("singleton")
public class MyRepository {
    private static ArrayList<MyBook> bookList = new ArrayList<>();

    public MyRepository(){
        for (int i = 1 ; i <= 10; i++) {
            bookList.add(new MyBook());
        }
    }

    public static ArrayList<MyBook> getBookList() {
        return bookList;
    }

    public static void addBook(MyBook newBook){
        bookList.add(newBook);
    }
    public static void removeBook(int id){
        for (MyBook book: bookList) {
            if (book.getId() == id){
                bookList.remove(book);
                break;
            }
        }
    }

    public static void changeBook(int id, MyBook newBook){
        for (MyBook book: bookList) {
            if (book.getId() == id){
                book = newBook;
                break;
            }
        }
    }
}
