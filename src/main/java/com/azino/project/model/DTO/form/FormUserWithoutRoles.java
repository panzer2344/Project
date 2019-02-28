package com.azino.project.model.DTO.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormUserWithoutRoles {

    @Id
    @Type(type = "java.lang.String")
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    private String name;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    private String lastName;

    @Min(1)
    @Max(150)
    private Integer age;

    @Transient
    public static final int NAME_MAX_LENGTH = 40;

    @Transient
    public static final int NAME_MIN_LENGTH = 1;

    @Transient
    public static final int PASSWORD_MIN_LENGTH = 1;

    @Transient
    public static final int PASSWORD_MAX_LENGTH = 30;
}
