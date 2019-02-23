package com.azino.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Image implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private byte[] data;
    @Type(type = "org.hibernate.type.TextType")
    @NotNull
    private String data;

    @ManyToOne
    @NotNull
    private User author;

    /*public Image(byte[] data, User author) {
        this.data = data;
        this.author = author;
    }*/

    public Image(String data, User author) {
        this.data = data;
        this.author = author;
    }

}
