CREATE TABLE public."Chat"
(
    id integer NOT NULL DEFAULT nextval('idchatupper'::regclass),
    name character varying(100) COLLATE pg_catalog."default",
    reciver_id integer,
    course_id integer,
    CONSTRAINT "Chat_pkey" PRIMARY KEY (id),
    CONSTRAINT "Chat_course_id_fkey" FOREIGN KEY (course_id)
        REFERENCES public."Course" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT reciver_chat FOREIGN KEY (reciver_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Chat"
    OWNER to deploy;