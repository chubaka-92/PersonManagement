create table if not exists person (
                         id       bigserial    not null,
                         name     varchar(50),             -- Имя персоны
                         age      integer,                 -- возраст персоны
                         email    varchar(100),            -- почта персоны
                         position varchar(100),            -- должность персоны
                         salary   numeric,                 -- ЗП персоны
                         experience   double precision,    -- Стаж работы на позиции

                         PRIMARY KEY (id)
);
--todo название некорректно. ALTER - это изменение, а у тебя CREATE + лучше добавить в название конкрентную созданную таблицу __CREATE_PERSON_TABLE

