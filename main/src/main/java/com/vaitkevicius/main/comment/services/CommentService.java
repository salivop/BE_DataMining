package com.vaitkevicius.main.comment.services;

import com.vaitkevicius.main.comment.data.db.Comment;
import com.vaitkevicius.main.comment.data.repositories.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comment findCommentById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void removeComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
