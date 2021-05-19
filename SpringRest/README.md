### commands to run and test

[postgres on docker]

docker run -d --name dev-postgres -e POSTGRES_PASSWORD=password -v /docker/postgres-data/:/var/lib/postgresql/data -p 5432:5432 postgres

docker exec -it dev-postgres bash

[psql - cli]

psql -h localhost -U postgres

CREATE DATABASE restapi;

\c restapi

CREATE TABLE public.blog (
	id serial NOT NULL,
	title varchar(500) NULL,
	"content" varchar(5000) NULL
);

[RUN]

* mvn spring-boot:run


[TEST]

[GET]

	http://localhost:9080/blog
	http://localhost:9080/blog/2

[POST]

	http://localhost:9080/blog
	    {
		  "title": "Sun",
		  "content": "Sun now Oracle"
	      }
[PUT]

	http://localhost:9080/blog/2
	    {
	      "title": "Oracle",
	      "content": "Oracle , Modified"
            }

[DELETE]

	http://localhost:9080/blog/3


