package com.nhnacademy.board.board.repository.impl;

import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.board.repository.BoardRepository;
import com.nhnacademy.board.common.pagenation.Page;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Repository
public class MemoryBoardRepository implements BoardRepository {
    private final ConcurrentMap<Long, Post> postMap = new ConcurrentHashMap<>();
    private long id = 1;
    @Override
    public long register(Post post) {
        post.setId(this.id);
        postMap.put(post.getId(), post);
        this.id++;

        return post.getId();
    }

    @Override
    public Post remove(long id) {
        return postMap.remove(id);
    }

    @Override
    public Post getPost(long id) {
        return postMap.get(id);
    }

    @Override
    public Page<Post> getPagedList(int page, int size) {
        return new Page<Post>() {
            @Override
            public int getPageNumber() {
                return page;
            }

            @Override
            public int getPageSize() {
                return size;
            }

            @Override
            public int getTotalPageCount() {
                return (int)Math.ceil( (getTotalCount()*1.0) /getPageSize());
            }

            @Override
            public long getTotalCount() {
                return postMap.size();
            }

            @Override
            public List<Post> getList() {
                List<Post> posts = postMap.values().stream()
                        .sorted(Comparator.comparing(Post::getWriteTime).reversed())
                        .collect(Collectors.toList());

                int start = (getPageNumber()-1)*getPageSize();
                int end = start + getPageSize();

                if(end > getTotalCount()){
                    end = (int) getTotalCount();
                }
                return posts.subList(start,end);
            }
        };
    }
}