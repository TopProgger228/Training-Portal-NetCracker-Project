CREATE TABLE public."UsersToken"
(
    id integer NOT NULL DEFAULT nextval('"UsersToken_id_seq"'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    token character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default",
    expiry_date date,
    CONSTRAINT "UsersToken_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."UsersToken"
    OWNER to deploy;