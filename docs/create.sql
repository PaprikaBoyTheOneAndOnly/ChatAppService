CREATE DATABASE chat_app CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE account(
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR (128) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE message (
    id BIGINT not null auto_increment,
    `fromUser` VARCHAR(20) NOT NULL,
    `toUser` VARCHAR(20) NOT NULL,
    message TEXT(65535) DEFAULT '',
    PRIMARY KEY (id),
    FOREIGN KEY (`fromUser`) REFERENCES account(username) ON DELETE CASCADE,
    FOREIGN KEY (`toUser`) REFERENCES account(username) ON DELETE CASCADE
);
