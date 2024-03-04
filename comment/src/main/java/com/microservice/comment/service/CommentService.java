package com.microservice.comment.service;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplateConfig restTemplate;


    public  Comment saveComment(Comment comment)
    {
//       Post post= restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/posts"+comment.getPostId(), Post.class);
         RestTemplate post = restTemplate.getRestTemplate("http://localhost:8081/api/posts/" + comment.getPostId(), Post.class);


        if(post!=null)   // if post object is null it means post is does not exist.... if post Object is not null post is exist
       {
           String commentId = UUID.randomUUID().toString();
           comment.setCommentId(commentId);
           Comment savedComment = commentRepository.save(comment);
       return savedComment;
       }
       else
       {
         return  null;
       }
    }

    // List of All Comments using postId
    public List<Comment> getAllCommentsById(String postId)
    {
        List<Comment>  comments = commentRepository.findByPostId(postId);
        return comments;
    }
}

// we are use "getForObject" because we are getting the Object. we are use getMethod "getmapping" from the post controller
// Post Object is not so we are creating post object in payload