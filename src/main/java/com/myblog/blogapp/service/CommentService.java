package com.myblog.blogapp.service;

import com.myblog.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {

    //creating comment
    CommentDto createComment(long postId, CommentDto commentDto);

    //getting all comments ByPostID
    List<CommentDto> getCommentByPostId(long postId);

    //updating comment
    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    //delete comment
    void deleteComment(long postId, long commentId);

}
