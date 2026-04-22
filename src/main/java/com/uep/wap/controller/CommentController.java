package com.uep.wap.controller;

import com.uep.wap.dto.CommentDTO;
import com.uep.wap.model.Comment;
import com.uep.wap.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(path = "/comments")
    public Iterable<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping(path = "/comments")
    public String addComment(@RequestBody CommentDTO commentDTO) {
        commentService.addComment(commentDTO);
        return "Comment added";
    }
}