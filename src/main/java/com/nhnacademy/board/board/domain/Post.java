package com.nhnacademy.board.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {
    private long id;
    private String title;
    private String content;
    private String writerUserId;
    private LocalDateTime writeTime;
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