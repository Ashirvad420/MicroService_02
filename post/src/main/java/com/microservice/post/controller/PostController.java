package com.microservice.post.controller;

import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;  // call service layer

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post)
    {
        Post newPost = postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    // find the post based on the post id... this is second method
    // http://localhost:8081/api/posts{postId}
    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable String postId)
    {
      Post post = postService.findPostById(postId);
        return post;
    }


    // http://localhost:8081/api/posts{postId}/comments

    // want to return post and Comments
    @GetMapping("/{postId}/comments")
    public ResponseEntity<PostDto> getPostWithComments(@PathVariable String postId) // if i modify Dto that nothing to do database
    {
        PostDto postDto = postService.getPostWithComments(postId);
        return  new ResponseEntity<>(postDto,HttpStatus.OK);
    }

}

// comment project interact with this "http://localhost:8081/api/posts{postId}" url and search whether the post is exist or not
/* mtlb jo mera comment project hai wo interact krega PostId ke url ke sath aur search krega post exist hai ya nhi to uske
    uske liye RestTemplate class ka use krenge
 */

// Dto can modify the response content to given postman.. if you modify entity database table get modify
// if i modify Dto that nothing to do database