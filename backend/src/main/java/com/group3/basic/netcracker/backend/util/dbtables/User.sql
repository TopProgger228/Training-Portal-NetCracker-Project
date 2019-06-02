CREATE TABLE public."User"
(   -- Collate pg_catalog.default задает порядок сортировки по умоланию
    username character varying COLLATE pg_catalog."default" NOT NULL,
    fname character varying(50) COLLATE pg_catalog."default" NOT NULL,
    lname character varying(50) COLLATE pg_catalog."default",
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    pass character varying(100) COLLATE pg_catalog."default",
    created_at date NOT NULL,
    photo character varying COLLATE pg_catalog."default",
    id integer NOT NULL DEFAULT nextval('"Users_id_seq"'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
   -- regclass системная функция, задает автоинкремент для поля
    manager_id integer,
    role character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "Users_pkey" PRIMARY KEY (id),
  -- UNIQUE задает уникальность поля 
    CONSTRAINT email_unique UNIQUE (email)
        INCLUDE(username),
    CONSTRAINT username_unique UNIQUE (username)
,    
  -- CONSTRAINT задает уникальность и FOREIGN KEY делает вторичный ключ 
    CONSTRAINT "User_manager_id_fkey" FOREIGN KEY (manager_id)
  -- Параметры для вторичного ключа 
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
-- Идентификатор объекта (Object Identifier, OID) используется внутри Postgres 
-- в качестве первичного ключа различных системных таблиц
WITH (
    OIDS = FALSE
)
-- Таблица принадлежит к области таблиц (TABLESPACE)
TABLESPACE pg_default;
-- Назначить владельцем таблицы нашего юзера БД 
ALTER TABLE public."User"
    OWNER to deploy;