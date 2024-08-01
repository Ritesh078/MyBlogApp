package com.myblog.blogapp.repository;

import com.myblog.blogapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>  {

 //find record based on postId automatically springboot does
 List<Comment> findByPostId(long postId);
 /*List<Comment> findByEmail(String email);
 List<Comment>findByName(String name);*/

}
