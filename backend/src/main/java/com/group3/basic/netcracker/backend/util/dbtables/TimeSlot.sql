CREATE TABLE public."TimeSlot"
(
    id integer NOT NULL DEFAULT nextval('"TimeSlot_id_seq"'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    start_time time(5) without time zone NOT NULL,
    end_time time(5) without time zone NOT NULL,
    week_day character varying(20) COLLATE pg_catalog."default",
    course_id integer,
    CONSTRAINT "TimeSlot_pkey" PRIMARY KEY (id),
    CONSTRAINT "TimeSlot_course_id_fkey" FOREIGN KEY (course_id)
        REFERENCES public."Course" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."TimeSlot"
    OWNER to deploy;