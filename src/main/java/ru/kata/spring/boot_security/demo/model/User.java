package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть между 2 и 30 символами")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*", message = "Только буквы русского и английского алфавита")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 30, message = "Фамилия должна быть между 2 и 30 символами")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*", message = "Только буквы русского и английского алфавита")
    @Column(name = "surname")
    private String surname;

    @Min(value = 0, message = "Значение возраста должно быть не менее 0")
    @Column(name = "age")
    private int age;

    @NotBlank(message = "Email не может быть пустой")
    @Email(message = "Email должен быть валидным")
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String surname, int age, String email, String password, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public @NotBlank(message = "Email не может быть пустой") @Email(message = "Email должен быть валидным") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email не может быть пустой") @Email(message = "Email должен быть валидным") String email) {
        this.email = email;
    }
}
