package com.nhnacademy.board.user.repository;

import com.nhnacademy.board.common.pagenation.Page;
import com.nhnacademy.board.entity.User;

import java.util.List;

public interface UserRepository {
    void add(User user);
    User remove(String id);
    User getUser(String id);
    List<User> getUsers();

    Page<User> getPagedList(int page, int size);
    boolean existById(String id);
}
