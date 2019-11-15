INSERT INTO `account` (`username`, `password`)
VALUES ('Admin', 'Admin'),
       ('Username', '1234');

INSERT INTO `message` (`message`, `sent_time`, `from_user`, `to_user`)
VALUES ('Hello There', '2019-06-24 12:01:00', 'Admin', 'Username'),
       ('General Kenobi', '2019-06-24 11:59:00', 'Username', 'Admin');

INSERT INTO `file` (`media_type`, `filename` ,`original_filename` ,`sent_time` ,`from_user` ,`to_user`)
VALUES ('text/plain', 'testfile-Admin-Username.txt', 'testfile.txt', '2019-06-24 12:00:00', 'Admin', 'Username');
