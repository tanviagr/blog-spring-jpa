package com.example.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) //post is the name of the owning field in the Comment class
        Set<Comment> comments;

//        If the relationship is bidirectional, the
// * <code> mappedBy</code> element must be used to specify the relationship field or
// * property of the entity that is the owner of the relationship.
}
