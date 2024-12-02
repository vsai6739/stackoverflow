package com.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "replay")
@NoArgsConstructor
public class Replay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 1000)
    private String content;

    private Integer upvotes = 0;

    @OneToMany(mappedBy = "replay", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
