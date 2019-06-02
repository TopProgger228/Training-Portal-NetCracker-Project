CREATE TABLE public."Lesson"
(
    id integer NOT NULL DEFAULT nextval('"Lesson_id_seq"'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    is_cancel boolean,
    course_id integer,
    lesson_date date,
    time_slot_id integer,
    CONSTRAINT "Lesson_pkey" PRIMARY KEY (id),
    CONSTRAINT "Lesson_course_id_fkey" FOREIGN KEY (course_id)
        REFERENCES public."Course" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "Lesson_time_slot_id_fkey" FOREIGN KEY (time_slot_id)
        REFERENCES public."TimeSlot" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Lesson"
    OWNER to deploy;