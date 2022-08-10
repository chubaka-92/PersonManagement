create table if not exists task (
                         id             bigserial    not null,
                         id_person      bigserial,
                         uid            varchar(100),       -- уникальный код задачи персоны
                         description    varchar(100),       -- описание задачи
                         priority       varchar(100),       -- должность персоны

                         PRIMARY KEY (id)
);

