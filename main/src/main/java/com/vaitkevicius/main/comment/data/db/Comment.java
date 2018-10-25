package com.vaitkevicius.main.comment.data.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comment")
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

    //private Map<String, String> wordWithValue;


}