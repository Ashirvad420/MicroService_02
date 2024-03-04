package com.microservice.post.service;

import com.microservice.post.config.RestTemplateConfig;
import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RestTemplateConfig restTemplate;

    public Post savePost(Post post) // this is method
    {
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
       Post savedPost = postRepository.save(post);
       return savedPost;
    }

    public Post findPostById(String postId) // create method in service layer
    {
       Post post = postRepository.findById(postId).get(); // it return option Optional then it use .get() method and convert into post Object
       return  post;
    }

    public PostDto getPostWithComments(String postId)
    {

       ArrayList comments = restTemplate.getRestTemplate().getForObject("http://localhost:8082/api/comments/"+postId, ArrayList.class);
        Post post = postRepository.findById(postId).get();
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setComments(comments);
       return postDto;
    }
}

// we are use "getForObject" because we are getting the Object. we are use getMethod "getmapping" from the post controller
// this url "http://localhost:8082/api/comments/" is returning  ' ArrayList.class '