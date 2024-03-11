/*INSERT INTO roles (role)
VALUES ('ROLE_ADMIN'),
       ('ROLE_POSTS'),
       ('ROLE_USERS'),
       ('ROLE_ALBUMS');*/
/*adminPassword
  postPassword
  userPassword
  albumPassword*/
INSERT INTO users (email, user_password, u_role)
VALUES ('admin@mail.ru', '$2a$10$CrtbqMDCDb4s2ZAG69lgXuGpoSVPKC5C1RNMVWFVuZt3/db30MD/.', 'ROLE_ADMIN'),
       ('post@mail.ru', '$2a$10$IPZOp4Z7GC6jGhlrfudonudH41ClkFYO3P2bHH.pwSTBTvcZvt/YW', 'ROLE_POSTS'),
       ('users@mail.ru', '$2a$10$4Rq5jfkKw2pYLepYof32pOHQxSzZlrKpL/2CWWPrlM2V6iBJf6yxq', 'ROLE_USERS'),
       ('albums@mail.ru', '$2a$10$hQn9suARm8MIrgEZ1I6GLOihPwnEr9TWiCZ7QoVOb0c2eok3qdjLC', 'ROLE_ALBUMS');