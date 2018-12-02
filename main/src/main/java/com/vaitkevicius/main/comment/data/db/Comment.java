package com.vaitkevicius.main.comment.data.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document(collection = "comment")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private String id;

    private String commentAuthor;
    private String date;
    private String comment;
    private List<String> words;

    private HashMap<String, Integer> commentWithValue;
}