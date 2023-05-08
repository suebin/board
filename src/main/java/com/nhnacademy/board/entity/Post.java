package com.nhnacademy.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "post_id")
    private long id;
    private String title;
    private String content;
    @Column(name = "writer")
    private String writerUserId;
    @Column(name = "write_time")
    private LocalDateTime writeTime;
    @Column(name = "view_count")
    private int viewCount;

    public Post(String title, String content, String writerUserId) {
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = LocalDateTime.now();
        viewCount = 0;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        return String.valueOf(id).equals("0") ?
                String.valueOf(id).equals(String.valueOf(post.id)) :
                String.valueOf(post.id).equals("0");
    }

    @Override
    public int hashCode() {
        return 0;
    }
}