package com.vaitkevicius.main.user.data.dto;

import com.vaitkevicius.main.role.data.db.Roles;
import com.vaitkevicius.main.common.validation.groups.Create;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Log4j2
@Data
@Builder
public class UserDto {

    @NotNull
    private ObjectId id;

    @NotNull
    @NotBlank
    @Email
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String email;
    @NotBlank(groups = {Create.class})
    private String password;

    @NotNull
    private String name;
    @NotNull
    private String surname;
    private String phoneNo;

    private List<Roles> roles;

}
