CREATE TABLE public."LessonMissing"
(
    id integer NOT NULL DEFAULT nextval('"LessonMissing_id_seq"'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    lesson_id integer,
    user_id integer,
    reason character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "LessonMissing_pkey" PRIMARY KEY (id),
    CONSTRAINT "LessonMissing_lesson_id_fkey" FOREIGN KEY (lesson_id)
        REFERENCES public."Lesson" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "LessonMissing_user_id_fkey" FOREIGN KEY (user_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."LessonMissing"
    OWNER to deploy;