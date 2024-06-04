package com.myblog.blogapp.controller;

import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping //Post The Data
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    @GetMapping //Get all the posts in restapi
    public List<PostDto> getAllPosts(  //pagination concept
         @RequestParam (value = "pageNo", defaultValue = "0", required = false) int pageNo,
         @RequestParam (value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return postService.getAllPosts(pageNo,pageSize);
    }


    //http://localhost:8080/api/posts/1
    @GetMapping("/{id}")  //Get Post by id
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        //PostDto dto = postService.getPostById(id);
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //http://localhost:8080/api/posts/1
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id) {
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1  delete mapping url
    @DeleteMapping("/{id}")   //delete post by id
    public ResponseEntity<String> deletePost(@PathVariable("id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post Entity Deleted Successfully.", HttpStatus.OK);
    }
}
