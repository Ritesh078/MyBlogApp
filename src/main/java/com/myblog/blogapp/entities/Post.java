package com.myblog.blogapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data //it generates getters & Setters
@NoArgsConstructor //create constructor without parameter
@AllArgsConstructor //generates constructor req. argument for every field in class
@Entity
@Table(
        name = "posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
      )
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generating ID
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false)
    private String description;

    @Lob //this annotation help us to store huge amount of data in it
    @Column(name="content",nullable = false)
    private String content; // String allowed 255 Characters




}
