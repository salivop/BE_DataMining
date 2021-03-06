package com.vaitkevicius.main.comment.data.repositories;

import com.vaitkevicius.main.comment.data.db.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment findOneById(String id);
    Comment findOneByDate(String date);

    Comment findFirstByDateIgnoreCase(String date);
    Comment findFirstByCommentAuthor(String commentAuthor);
}
