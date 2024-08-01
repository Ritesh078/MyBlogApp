package com.myblog.blogapp.service.impl;

import com.myblog.blogapp.entities.Comment;
import com.myblog.blogapp.entities.Post;
import com.myblog.blogapp.exception.ResourceNotFoundException;
import com.myblog.blogapp.payload.CommentDto;
import com.myblog.blogapp.repository.CommentRepository;
import com.myblog.blogapp.repository.PostRepository;
import com.myblog.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper; //converting entity<->dto using mapper

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));

        Comment comment = mapToComment(commentDto);

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override //getting comments ByPostId
    public List<CommentDto> getCommentByPostId(long postId) {
        // Fetch comments for the given postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // Convert Comment entities to CommentDto objects
        return comments.stream()
                .map(comment -> mapToDto(comment)) // Assuming you have a toDto method in CommentRepository
                .collect(Collectors.toList());

    }

    @Override //Update Comment service
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
        // Fetch the Post (retrieve post entity by id)
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        // retrieve comment by id
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id));


       // Update the Comment with values from the CommentDto
        comment.setName(commentDto.getName());   // Use commentDto's name
        comment.setEmail(commentDto.getEmail()); // Use commentDto's email
        comment.setBody(commentDto.getBody());   // Use commentDto's body

        // Save the updated Comment
        Comment updatedComment = commentRepository.save(comment);

        // Return the updated CommentDto
        return mapToDto(updatedComment);
    }

    @Override //delete comment service logic
    public void deleteComment(long postId, long commentId) {
        // whether check postBy id if not throw exception
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        // whether check commentBy id if not throw exception
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId));

        commentRepository.deleteById(commentId);

    }

    //Converting entity to  Dto
    Comment mapToComment(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);

//        Comment comment = new Comment();
//       comment.setName(commentDto.getName());
//       comment.setEmail(commentDto.getEmail());
//       comment.setBody(commentDto.getBody());
       return comment;

    }

    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);

//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(commentDto.getBody());
         return commentDto;
    }
}
