CREATE TABLE public."Group"
(
    course_id integer,
    user_id integer,
    CONSTRAINT "Group_course_id_fkey" FOREIGN KEY (course_id)
        REFERENCES public."Course" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "Group_user_id_fkey" FOREIGN KEY (user_id)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Group"
    OWNER to deploy;