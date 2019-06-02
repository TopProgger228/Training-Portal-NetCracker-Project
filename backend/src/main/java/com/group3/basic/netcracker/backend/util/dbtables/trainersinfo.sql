CREATE TABLE public.trainersinfo
(
    trainer_id integer,
    info character varying(500) COLLATE pg_catalog."default",
    CONSTRAINT trainersinfo_trainer_id_fkey FOREIGN KEY (trainer_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.trainersinfo
    OWNER to deploy;