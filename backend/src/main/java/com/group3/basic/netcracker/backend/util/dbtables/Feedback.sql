CREATE TABLE public."FeedBack"
(
    id integer NOT NULL DEFAULT nextval('idfeedbackupper'::regclass),
    feedback_text text COLLATE pg_catalog."default" NOT NULL,
    user_id integer NOT NULL,
    course_id integer NOT NULL,
    CONSTRAINT "Feedback_pkey" PRIMARY KEY (id),
    CONSTRAINT feedback_course FOREIGN KEY (course_id)
        REFERENCES public."Course" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT feedback_user FOREIGN KEY (user_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."FeedBack"
    OWNER to deploy;