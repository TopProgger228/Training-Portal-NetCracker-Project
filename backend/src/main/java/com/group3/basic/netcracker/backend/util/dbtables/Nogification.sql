CREATE TABLE public."Notification"
(
    id integer NOT NULL DEFAULT nextval('idupper'::regclass),
    notify_text character varying(250) COLLATE pg_catalog."default",
    notify_type character varying(100) COLLATE pg_catalog."default",
    user_id integer,
    CONSTRAINT "Notification_pkey" PRIMARY KEY (id),
    CONSTRAINT user_notify FOREIGN KEY (user_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Notification"
    OWNER to deploy;