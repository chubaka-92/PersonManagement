create table if not exists roles (
                         id      bigserial not null,
                         name    varchar(100) unique,       -- Нименование роли

                         PRIMARY KEY (id)
);