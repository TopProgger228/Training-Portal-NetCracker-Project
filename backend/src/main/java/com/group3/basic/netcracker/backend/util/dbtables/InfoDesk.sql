CREATE TABLE public."InfoDesk"
(
    id integer NOT NULL DEFAULT nextval('idinfodeskupper'::regclass),
    created_at timestamp without time zone,
    status character varying(100) COLLATE pg_catalog."default",
    user_id integer NOT NULL,
    CONSTRAINT "InfoDesk_pkey" PRIMARY KEY (id),
    CONSTRAINT user_infodesk FOREIGN KEY (user_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."InfoDesk"
    OWNER to deploy;