CREATE DATABASE restapi;

\c restapi

CREATE TABLE public.blog (
	id serial NOT NULL,
	title varchar(500) NULL,
	"content" varchar(5000) NULL
);
