package com.example.blog.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts", uniqueConstraints = {
        @UniqueConstraint(columnNames =  {
                "title"
        })
})
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

        public Post(Long id, String title, String description, String content) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.content = content;
        }

        public Post() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        @Override
        public String toString() {
                return "Post{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", content='" + content + '\'' +
                        '}';
        }
}
