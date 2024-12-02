package com.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "discussions")
@NoArgsConstructor
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "discussion_tag",
            joinColumns = @JoinColumn(name = "discussion_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> discussionTags;

    private Integer upvotes = 0;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Replay> replays;


    public void addTag(Tag tag){
        if(discussionTags==null){
            discussionTags=new ArrayList<>();
        }
        discussionTags.add(tag);
    }
}
