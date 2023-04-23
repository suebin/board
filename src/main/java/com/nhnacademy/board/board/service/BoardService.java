package com.nhnacademy.board.board.service;

import com.nhnacademy.board.board.domain.Post;
import com.nhnacademy.board.board.domain.PostRequest;
import com.nhnacademy.board.board.repository.BoardRepository;
import com.nhnacademy.board.common.pagenation.Page;
import com.nhnacademy.board.exception.PostNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Post getPost(Long id){
        Post post = boardRepository.getPost(id);
        if(Objects.isNull(post)){
            throw new PostNotFoundException();
        }
        return post;
    }

    public Page<Post> getPostList(int page, int size){
        return boardRepository.getPagedList(page, size);
    }

    public void register(PostRequest postRequest) {
        Post post = new Post(postRequest.getTitle(),postRequest.getContent(), postRequest.getWriterUserId());
        boardRepository.register(post);
    }

    public void update(PostRequest postRequest){
        Post post = boardRepository.getPost(postRequest.getId());
        post.update(postRequest.getTitle(), postRequest.getContent());
    }

    public void delete(Long id){
        boardRepository.remove(id);
    }

}
