package com.nhnacademy.board.entity;

import com.nhnacademy.board.config.RootConfig;
import com.nhnacademy.board.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class EntityManagerTest {
    @PersistenceContext
    EntityManager entityManager;

    @Test
    void test1() {
        User user1 = User.createUser("user", "1234", "유저", "user.png");
        entityManager.persist(user1);

        User user2 = entityManager.find(User.class, "user");
        assertThat(user2).isEqualTo(user1);
    }

    @Test
    void test2() {
        User user1 = new User();
        user1.setId("user1");
        user1.setPassword("1234");
        user1.setCreatedAt(LocalDateTime.now());

        entityManager.persist(user1);

        User user2 = entityManager.find(User.class, "user1");
        assertThat(user2).isEqualTo(user1);

        user2.setPassword("4321");
        entityManager.flush();
    }

    @Test
    void test3() {
        User user1 = new User();
        user1.setId("user1");
        user1.setPassword("1234");
        user1.setCreatedAt(LocalDateTime.now());

        entityManager.persist(user1);

        entityManager.close();

        User user2 = entityManager.find(User.class, "user1");
        assertThat(user2).isEqualTo(user1);
    }
}
