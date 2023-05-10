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

MERGE INTO `Users` KEY ( `user_id` ) VALUES ( 'user', '1234', '유저', 'user.png', 'USER', now());
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