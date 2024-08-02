package org.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "posts",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "title"
                )
        }
)

public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message ="title should not be empty or null")
    @Size(min = 2, message = "title should be at least two characters long")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message ="title should not be empty or null")
    @Size(min = 2, message = "title should be at least two characters long")
    @Column(name = "description", nullable = false)
    private String description;

    @NotEmpty(message ="title should not be empty or null")
    @Size(min = 2, message = "title should be at least two characters long")
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Comment> comments = new HashSet<>();
}
