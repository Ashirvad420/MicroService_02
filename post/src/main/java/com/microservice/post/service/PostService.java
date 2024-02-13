package com.microservice.post.service;

import com.microservice.post.entity.Post;
import com.microservice.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) // this is method
    {
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
       Post savedPost = postRepository.save(post);
       return savedPost;
    }

    public Post findPostById(String postId) // create method in service layer
    {
       Post post = postRepository.findById(postId).get();
       return  post;
    }
}
