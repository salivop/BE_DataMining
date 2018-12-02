package com.vaitkevicius.main.eComments.data.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * *
 * Created By Povilas 02/12/2018
 * *
 **/
@Document(collection = "ecomments" +
        "")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ecomments {
    @Id
    private String id;

    private String eComment;
}
