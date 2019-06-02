CREATE TABLE public."Message"
(
    id integer NOT NULL DEFAULT nextval('idmessageupper'::regclass),
    msg_text text COLLATE pg_catalog."default",
    send_at timestamp without time zone,
    info_desk_id integer,
    sender_id integer NOT NULL,
    status character varying COLLATE pg_catalog."default",
    chat_id integer,
    CONSTRAINT "Message_pkey" PRIMARY KEY (id),
    CONSTRAINT chat_message FOREIGN KEY (chat_id)
        REFERENCES public."Chat" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT info_desk_message FOREIGN KEY (info_desk_id)
        REFERENCES public."InfoDesk" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT sender_message FOREIGN KEY (sender_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Message"
    OWNER to deploy;