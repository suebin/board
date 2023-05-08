package com.nhnacademy.board.config;

import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.board.repository.BoardRepository;
import com.nhnacademy.board.board.repository.impl.MemoryBoardRepository;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.user.repository.UserRepository;
import com.nhnacademy.board.user.repository.impl.MemoryUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

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
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/spring-jpa;DATABASE_TO_UPPER=false;"
                + "INIT=RUNSCRIPT FROM 'classpath:/script/schema.sql'");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(10);
        dataSource.setMinIdle(10);
        dataSource.setMaxIdle(10);

        dataSource.setMaxWaitMillis(1000);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
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
