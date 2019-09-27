INSERT INTO `account` (`username`, `password`)
VALUES ('Admin', 'Admin'),
       ('Username', '1234');

INSERT INTO `message` (`id`, `from_user`, `to_user`, `message`)
VALUES (1, 'Admin', 'Username', 'Hello There'),
       (2, 'Username', 'Admin', 'General Kenobi'),
       (3, 'Username', 'Admin', 'You are a bold One!');
