package com.justindev.securenotes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @Column(name = "content", columnDefinition="TEXT")
    private String content;

    private String ownerUsername;

}
