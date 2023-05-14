package com.nhnacademy.board.entity;

import com.nhnacademy.board.config.RootConfig;
import com.nhnacademy.board.config.WebConfig;
import com.nhnacademy.board.repository.BoardRepository;
import com.nhnacademy.board.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class BoardRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test1() {
        User user12 = User.createUser("user12", "1234", "유저12", "user.png");
        userRepository.save(user12);

        Post post = new Post();

        Post.Pk pk = new Post.Pk();
        pk.setId(1L);
        post.setPk(pk);
        post.setUser(user12);

        post.setTitle("title");
        post.setWriteTime(LocalDateTime.now());

        boardRepository.save(post);
        assertThat(boardRepository.findAll().size()).isEqualTo(1);
    }
}
