package org.app.srms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    private String userName;

    private int age;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;


    @Column(nullable = true)
    private String Department;
}
