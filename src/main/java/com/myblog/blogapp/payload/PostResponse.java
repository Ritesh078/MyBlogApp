package com.myblog.blogapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//for getting pagination details information
@Data //for getter setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse  {

    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;



}
