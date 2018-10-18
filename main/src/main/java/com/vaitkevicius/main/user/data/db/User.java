package com.vaitkevicius.main.user.data.db;

import com.vaitkevicius.main.common.data.AbstractEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity {

    @Id
    private String id;

    private String login;
    private String password;

    private String name;
    private String surname;
    private String email;
    private String phoneNo;
}
