CREATE TABLE `account`(
    `username` VARCHAR(20) NOT NULL UNIQUE,
    `password` VARCHAR (128) NOT NULL,
    PRIMARY KEY (`username`)
);

CREATE TABLE `file`(
     `id` BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
     `media_type` VARCHAR(20) NOT NULL,
     `filename` VARCHAR(50) NOT NULL,
     `original_filename` VARCHAR(50) NOT NULL,
     `sent_time` TIMESTAMP NOT NULL,
     `from_user` VARCHAR(20) NOT NULL,
     `to_user` VARCHAR(20) NOT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY (`from_user`) REFERENCES account(`username`) ON DELETE CASCADE,
     FOREIGN KEY (`to_user`) REFERENCES account(`username`) ON DELETE CASCADE
);

CREATE TABLE `message` (
    `id` BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    `message` TEXT(65535) DEFAULT '',
    `sent_time` TIMESTAMP NOT NULL,
    `from_user` VARCHAR(20) NOT NULL,
    `to_user` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`from_user`) REFERENCES account(`username`) ON DELETE CASCADE,
    FOREIGN KEY (`to_user`) REFERENCES account(`username`) ON DELETE CASCADE
);
