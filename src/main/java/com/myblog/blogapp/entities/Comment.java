package com.myblog.blogapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//creating comment API
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    private  String email;
    private  String name;

    @ManyToOne(fetch = FetchType.LAZY)  //mapping
    @JoinColumn(name = "post_id",nullable = false) //Foreign Key to Join Columns
    private Post post;
}
