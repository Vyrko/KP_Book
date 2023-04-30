package com.example.KP_Book.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany( mappedBy = "genres",fetch = FetchType.LAZY)
    private List<Book> books;
}
