CREATE TABLE public."News"
(
    id integer NOT NULL DEFAULT nextval('idnewsupper'::regclass),
    title character varying(200) COLLATE pg_catalog."default",
    create_date date,
    context text COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    CONSTRAINT "News_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."News"
    OWNER to deploy;