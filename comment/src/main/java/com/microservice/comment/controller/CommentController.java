package com.microservice.comment.controller;


import com.microservice.comment.entity.Comment;
import com.microservice.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // http://localhost:8082/api/comments
    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment)
    {
       Comment c = commentService.saveComment(comment);
       return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // How to get Comments id using PostId
    @GetMapping("{postId}")
    public List<Comment> getAllCommentsById(@PathVariable String postId)
    {
        List<Comment> comments = commentService.getAllCommentsById(postId);
        return comments;
    }
}
