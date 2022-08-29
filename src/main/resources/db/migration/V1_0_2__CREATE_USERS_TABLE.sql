create table if not exists users (
                         id         bigserial   not null,
                         user_name  varchar(50) unique,             -- Имя пользователя
                         password   varchar(255),                   -- Пароль
                         email      varchar(100) unique,            -- почта пользователя

                         PRIMARY KEY (id)
);

