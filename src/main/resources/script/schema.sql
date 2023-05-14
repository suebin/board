DROP TABLE IF EXISTS Posts;
DROP TABLE IF EXISTS Users;

CREATE TABLE IF NOT EXISTS Users
(
    `user_id`           VARCHAR(20) PRIMARY KEY,
    `password`          VARCHAR(20) NOT NULL,
    `name`              VARCHAR(30),
    `profile_file_name` VARCHAR(100),
    `role`              VARCHAR(20),
    `created_at`        DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user1', '1234', '유저1', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user2', '1234', '유저2', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user3', '1234', '유저3', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user4', '1234', '유저4', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user5', '1234', '유저5', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user6', '1234', '유저6', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user7', '1234', '유저7', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user8', '1234', '유저8', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user9', '1234', '유저9', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user10', '1234', '유저10', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user11', '1234', '유저11', 'user.png', 'USER', now());
MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'admin', '1234', '관리자', 'admin.png', 'ADMIN', now());


CREATE TABLE IF NOT EXISTS Posts
(
    `post_id`    BIGINT PRIMARY KEY AUTO_INCREMENT,
    `title`      VARCHAR(100) NOT NULL,
    `content`    TEXT,
    `writer`     VARCHAR(20),
    `write_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `view_count` int,
    FOREIGN KEY (writer) REFERENCES Users (user_id)
);