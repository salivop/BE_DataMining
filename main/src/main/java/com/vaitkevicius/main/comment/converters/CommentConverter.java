package com.vaitkevicius.main.comment.converters;

import com.vaitkevicius.main.comment.data.db.Comment;
import com.vaitkevicius.main.comment.data.dto.CommentDto;
import com.vaitkevicius.main.common.converter.AbstractConverter;

public class CommentConverter extends AbstractConverter<Comment, CommentDto> {

    @Override
    public CommentDto convertToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .commentAuthor(comment.getCommentAuthor())
                .cleanedComment(comment.getCleanedComment())
                .date(comment.getDate())
                .comment(comment.getComment())
                .words(comment.getWords())
                .mainWordFormsLT(comment.getMainWordFormsLT())
                .mainWordFormsLTRoots(comment.getMainWordFormsLTRoots())
                .positiveCommentValue(comment.getPositiveCommentValue())
                .negativeCommentValue(comment.getNegativeCommentValue())
                .build();
    }

    @Override
    public Comment convertToEntity(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .commentAuthor(commentDto.getCommentAuthor())
                .cleanedComment(commentDto.getCleanedComment())
                .date(commentDto.getDate())
                .comment(commentDto.getComment())
                .words(commentDto.getWords())
                .mainWordFormsLT(commentDto.getMainWordFormsLT())
                .mainWordFormsLTRoots(commentDto.getMainWordFormsLTRoots())
                .positiveCommentValue(commentDto.getPositiveCommentValue())
                .negativeCommentValue(commentDto.getNegativeCommentValue())
                .build();
    }
}
