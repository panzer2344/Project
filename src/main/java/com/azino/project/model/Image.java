package com.azino.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Image implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] data;

    @ManyToOne
    private User author;

    public Image(byte[] data, User author) {
        this.data = data;
        this.author = author;
    }

}
