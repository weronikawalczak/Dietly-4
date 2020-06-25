package com.weronika.dietly.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    public Message(String title, String content)  {
        this.title = title;
        this.content = content;
    }
}
