package com.nhnacademy.board.board.repository;

import com.nhnacademy.board.board.domain.Post;
import com.nhnacademy.board.common.pagenation.Page;

public interface BoardRepository {
    long register(Post post);


    Post remove(long id);

    Post getPost(long id);

    Page<Post> getPagedList(int page, int size);
}

