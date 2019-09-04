CREATE DATABASE chat_app CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE account (
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR (128) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE message (
    id BIGINT not null auto_increment,
    `from` VARCHAR(20) NOT NULL,
    `to` VARCHAR(20) NOT NULL,
    message TEXT(65535) DEFAULT '',
    PRIMARY KEY (id),
    FOREIGN KEY (`from`) REFERENCES account(username) ON DELETE CASCADE,
    FOREIGN KEY (`to`) REFERENCES account(username) ON DELETE CASCADE
);
