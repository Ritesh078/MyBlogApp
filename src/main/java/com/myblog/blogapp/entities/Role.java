package com.myblog.blogapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter //we can use @Data also directly
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
