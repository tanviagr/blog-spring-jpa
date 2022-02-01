package com.example.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "posts", uniqueConstraints = {
        @UniqueConstraint(columnNames =  {
                "title"
        })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "title", nullable = false)
        private String title;

        @Column(name = "description", nullable = false)
        private String description;

        @Column(name = "content", nullable = false)
        private String content;
}
