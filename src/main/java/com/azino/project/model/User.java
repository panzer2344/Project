package com.azino.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class User implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String firstName;

    private String lastName;

    private Integer age;

    /*private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;*/


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, String firstName, String lastName, Integer age) {
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
