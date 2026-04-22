package com.uep.wap.service;

import com.uep.wap.model.Comment;
import com.uep.wap.repository.CommentRepository;
import com.uep.wap.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void addComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        commentRepository.save(comment);
        System.out.println("Comment added");
    }

    public Iterable<Comment> getAllComments(){
        return commentRepository.findAll();
    }
}
