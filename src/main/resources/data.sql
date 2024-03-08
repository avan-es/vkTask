INSERT INTO roles (role)
VALUES ('ROLE_ADMIN'),
       ('ROLE_POSTS'),
       ('ROLE_USERS'),
       ('ROLE_ALBUMS');

INSERT INTO users (email, user_password)
VALUES ('admin@mail.ru', 'adminPassword'),
       ('post@mail.ru', 'postPassword'),
       ('users@mail.ru', 'usersPassword'),
       ('albums@mail.ru', 'albumsPassword');