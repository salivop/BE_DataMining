package com.vaitkevicius.main.word.data.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "word")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    @Id
    private String id;

    private String word;
    private int wordValue;
}
