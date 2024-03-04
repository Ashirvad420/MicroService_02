package com.microservice.comment.repository;

import com.microservice.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,String> {

    // create custom method to get comments id using postId
    List<Comment> findByPostId(String postId); // this abstract method convert into HQL Query
}
