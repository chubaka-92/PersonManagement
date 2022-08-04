create table if not exists personDto (
                         id       bigserial    not null,
                         name     varchar(50),             -- Имя персоны
                         age      integer,                 -- возраст персоны
                         email    varchar(100),            -- почта персоны
                         position varchar(100),            -- должность персоны
                         salary   numeric,                 -- ЗП персоны
                         experience   double precision,    -- Стаж работы на позиции

                         PRIMARY KEY (id)
);

