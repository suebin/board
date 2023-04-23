package com.nhnacademy.board.config;

import com.nhnacademy.board.board.domain.Post;
import com.nhnacademy.board.board.repository.BoardRepository;
import com.nhnacademy.board.board.repository.impl.MemoryBoardRepository;
import com.nhnacademy.board.user.domain.User;
import com.nhnacademy.board.user.repository.UserRepository;
import com.nhnacademy.board.user.repository.impl.MemoryUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = {com.nhnacademy.board.Base.class}, excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new MemoryUserRepository();
        userRepository.add(User.createAdmin("admin", "1234", "수빈", null));
        for (int i = 1; i <= 100; i++) {
            userRepository.add(User.createUser("user" + i, "1234", "유저" + i, null));
        }
        return userRepository;
    }

    @Bean
    public BoardRepository boardRepository() {
        BoardRepository boardRepository = new MemoryBoardRepository();
        for (int i = 1; i <= 100; i++) {
            boardRepository.register(new Post("제목" + i, "내용" + 1, "user" + i));
        }
        return boardRepository;
    }

}
