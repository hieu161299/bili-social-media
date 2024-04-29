package com.bilibili.controller;

import com.bilibili.models.Comment;
import com.bilibili.models.User;
import com.bilibili.service.CommentService;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @PostMapping("/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt ,
                                 @PathVariable("postId") Integer postId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComment(comment , postId , user.getId());

        return createdComment;
    }

    @PutMapping("/like/{commentId}")
    public Comment likeComment( @RequestHeader("Authorization") String jwt ,
                                 @PathVariable("commentId") Integer commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment likedComment = commentService.likeComment(commentId , user.getId());
        return likedComment;
    }

}
