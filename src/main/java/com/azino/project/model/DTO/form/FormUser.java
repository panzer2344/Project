package com.azino.project.model.DTO.form;

import com.azino.project.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormUser {

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

    //private Boolean active;

    //@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    //@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    //@Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    @Size(min = 1)
    private Set<Role> roles;

    @Transient
    public static final int NAME_MAX_LENGTH = 40;

    @Transient
    public static final int NAME_MIN_LENGTH = 1;

    @Transient
    public static final int PASSWORD_MIN_LENGTH = 1;

    @Transient
    public static final int PASSWORD_MAX_LENGTH = 30;

    public FormUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public FormUser(String name, String password, String firstName, String lastName, Integer age) {
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

}
