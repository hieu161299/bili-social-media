package com.bilibili.controller;

import com.bilibili.models.Post;
import com.bilibili.response.ApiResponse;
import com.bilibili.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        Post newPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(newPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> findPostByIdHandle(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) throws Exception {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Post>> findAllPost()  {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
    @PutMapping("/save/{postId}/user/{userId}")
    public ResponseEntity<?> savedPostHandle(@PathVariable Integer postId , @PathVariable Integer userId ) throws Exception {
        Post post = postService.savedPost(postId , userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<?> likedPostHandle(@PathVariable Integer postId , @PathVariable Integer userId ) throws Exception {
        Post post = postService.likePost(postId , userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }


}
