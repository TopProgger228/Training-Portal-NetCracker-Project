CREATE TABLE public."TrainerAttach"
(
    id integer NOT NULL DEFAULT nextval('idupper'::regclass),
    text text COLLATE pg_catalog."default",
    file bytea,
    user_id integer,
    course_id integer,
    CONSTRAINT "TrainerAttach_pkey" PRIMARY KEY (id),
    CONSTRAINT course_id FOREIGN KEY (course_id)
        REFERENCES public."Course" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
