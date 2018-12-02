package com.vaitkevicius.main.comment.services;

import com.vaitkevicius.main.comment.converters.CommentConverter;
import com.vaitkevicius.main.comment.data.db.Comment;
import com.vaitkevicius.main.comment.data.repositories.CommentRepository;
import com.vaitkevicius.main.common.utils.ExceptionFactory;
import com.vaitkevicius.main.word.data.repository.WordRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Log4j2
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    private ExceptionFactory exceptionFactory;

    public Comment getComment(String id) {
        Comment comment = commentRepository.findOneById(id);
        if (comment == null) {
            throw exceptionFactory.getResourseNotFoundException("message.error.commentNotFound", id);

        }
        return comment;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment saveComment(Comment comment) {
        HashMap<String, Integer> words = new HashMap<String, Integer>();

        String[] arr = comment.getComment().split("[^A-Za-zčąįžęėūųšČĄĮŽĘĖŪŲŠ]+$");
        for (int i = 0; i < arr.length; i += 1) {
            words.put(arr[i].toLowerCase(), Integer.valueOf(wordRepository.findFirstByWord(arr[i]).getWordValue()));
        }
        comment.setCommentWithValue(words);
        return commentRepository.save(comment);
    }


    public Comment saveOnlyComments(Comment comment ){
        List<String> words = new ArrayList<>();

        String[] arr = comment.getComment().split("[^A-Za-zčąįžęėūųšČĄĮŽĘĖŪŲŠ]");
        for (int i = 0; i < arr.length; i += 1) {
            if (!arr[i].trim().isEmpty()) {
                words.add(arr[i].toLowerCase());
            }
        }
        comment.setWords(words);
        return commentRepository.save(comment);
    }

    public void deleteComment(String id) {
        Comment comment = commentRepository.findOneById(id);

        if (comment != null) {
            commentRepository.delete(comment);
        }
    }
}
