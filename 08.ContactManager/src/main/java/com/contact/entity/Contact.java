package com.contact.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String nickname;
    private String phoneNo;
    private String work;
    @Column(length = 1000)
    private String description;
    private String image;
    @ManyToOne
    private User user;
}
