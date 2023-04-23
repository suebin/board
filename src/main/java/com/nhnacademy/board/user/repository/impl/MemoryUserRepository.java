package com.nhnacademy.board.user.repository.impl;

import com.nhnacademy.board.common.pagenation.Page;
import com.nhnacademy.board.user.domain.User;
import com.nhnacademy.board.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Slf4j
public class MemoryUserRepository implements UserRepository {
    private final ConcurrentMap<String, User> userMap = new ConcurrentHashMap<>();


    @Override
    public void add(User user) {
        userMap.put(user.getId(),user);
    }

    @Override
    public User remove(String id) {
        return userMap.remove(id);
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Page<User> getPagedList(int page, int size) {
        return new Page<User>() {
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
                return userMap.size();
            }

            @Override
            public List<User> getList() {
                List<User> users = userMap.values().stream()
                        .sorted(Comparator.comparing(User::getCreatedAt).reversed())
                        .collect(Collectors.toList());

                int start = (getPageNumber()-1)*getPageSize();
                int end = start + getPageSize();

                if(end > getTotalCount()){
                    end = (int) getTotalCount();
                }

                log.info("user-page-start:{}",start);
                log.info("user-page-end:{}",end);

                return users.subList(start,end);
            }
        };
    }

    @Override
    public boolean existById(String id) {
        return userMap.containsKey(id);
    }

}
