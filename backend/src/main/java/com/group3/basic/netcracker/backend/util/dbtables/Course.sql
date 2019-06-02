CREATE TABLE public."Course"
(
    id integer NOT NULL DEFAULT nextval('"Course_id_seq"'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    info text COLLATE pg_catalog."default",
    trainer_id integer,
    skill_level character varying(50) COLLATE pg_catalog."default" NOT NULL,
    start_date date,
    end_date date,
    qty_per_week integer,
    CONSTRAINT "Course_pkey" PRIMARY KEY (id),
    CONSTRAINT "Course_user_id_fkey" FOREIGN KEY (trainer_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Course"
    OWNER to deploy;
