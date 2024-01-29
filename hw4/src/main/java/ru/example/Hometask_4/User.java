package ru.example.Hometask_4;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, length = 60)
    private String email;

    public User(){
        this.name = "";
        this.age = 0;
        this.email = "";
    }

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;

        if (age != user.age) return false;
        if (!Objects.equals(name, user.name)) return false;
        return Objects.equals(email, user.email);
    }

    @Override
    public int compareTo(Object object) {
        return name.compareTo(((User)(object)).getName());
    }
}