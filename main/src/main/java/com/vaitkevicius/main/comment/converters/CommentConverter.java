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
                .date(comment.getDate())
                .comment(comment.getComment())
                .build();
    }

    @Override
    public Comment convertToEntity(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .commentAuthor(commentDto.getCommentAuthor())
                .date(commentDto.getDate())
                .comment(commentDto.getComment())
                .build();
    }
}
