package com.nhnacademy.board.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@ToString
public class User {
    public enum Role {
        ADMIN, USER
    }

    private String id;
    private String password;
    private String name;
    private String profileFileName;
    private Role role;

    private LocalDateTime createdAt;
    private User(String id, String password, String name, Role role, String profileFileName){
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.profileFileName = profileFileName;
        this.createdAt = LocalDateTime.now();
    }

    public static User createAdmin(String id, String password, String name, String profileFileName){
        return new User(id,password,name,Role.ADMIN, profileFileName);
    }
    public static User createUser(String id, String password, String name, String profileFileName) {
        return new User(id, password, name, Role.USER, profileFileName);
    }

    public void update(String password, String name, Role role){
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public void updateProfileName(String profileFileName){
        this.profileFileName = profileFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
