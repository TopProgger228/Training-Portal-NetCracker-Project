CREATE TABLE public."Schedule"
(
    id integer NOT NULL DEFAULT nextval('"StudySchedule_id_seq"'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer,
    time_slot_id integer,
    is_choosen boolean,
    CONSTRAINT "StudySchedule_pkey" PRIMARY KEY (id),
    CONSTRAINT "StudySchedule_time_slot_id_fkey" FOREIGN KEY (time_slot_id)
        REFERENCES public."TimeSlot" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "StudySchedule_user_id_fkey" FOREIGN KEY (user_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Schedule"
    OWNER to deploy;