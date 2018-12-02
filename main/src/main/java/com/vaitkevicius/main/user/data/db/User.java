package com.vaitkevicius.main.user.data.db;

import com.vaitkevicius.main.role.data.db.Roles;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  {

    @Id
    private ObjectId id;

    private String email;
    private String password;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private String name;
    private String surname;
    private String phoneNo;

    private List<Roles> roles;

}
