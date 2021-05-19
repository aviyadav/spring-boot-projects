### Running application

* mvn spring-boot:run

### DB Scripts

Create table and load the data

-- Drop table

 DROP TABLE sample.students;

-- Create table

CREATE TABLE sample.students (
	id INTEGER NOT NULL,
	name varchar(100) NULL,
	age INTEGER NULL,
	weight INT NULL,
	CONSTRAINT students_PK PRIMARY KEY (id)
)
ENGINE=InnoDB;


-- Seed Data

INSERT INTO `students` (`id`,`name`,`age`,`weight`) VALUES (1,"Rajah",82,62),(2,"Zia",27,159),(3,"Lyle",34,116),(4,"Ray",42,102),(5,"Zelda",56,129),(6,"Daryl",57,111),(7,"Madison",54,156),(8,"Sydnee",74,55),(9,"Alexandra",21,62),(10,"Garrett",55,96);

INSERT INTO `students` (`id`,`name`,`age`,`weight`) VALUES (11,"Desiree",84,70),(12,"Fleur",68,69),(13,"Shelly",54,98),(14,"Marsden",58,107),(15,"Melyssa",53,89),(16,"Amos",44,102),(17,"Whilemina",63,157),(18,"Haviva",85,116),(19,"Charity",25,150),(20,"Melanie",68,92);

INSERT INTO `students` (`id`,`name`,`age`,`weight`) VALUES (21,"Yvonne",47,91),(22,"Travis",62,61),(23,"Chava",54,119),(24,"Travis",64,71),(25,"Winifred",59,139),(26,"Boris",34,51),(27,"Brenden",32,123),(28,"Hammett",32,106),(29,"Russell",68,132),(30,"Jerry",78,110);

-- checks
select * from students s ;

select count(*) from students s ;


### Running the tests

* [List all] curl -v localhost:8080/students 
* [find 1] curl -v localhost:8080/students/1 
* [add] curl -X POST localhost:8080/addStudent -H ‘Content-type:application/json’ -d ‘{“id”:21,”name”:”Ramu”,”age”:18,”weight”:47}’ 
* [update] curl -X PUT localhost:8080/students/3 -H ‘Content-type:application/json’ -d ‘{“name”:”Virat”,”age”:18,”weight”:47}’ 
* [delete] curl -X DELETE localhost:8080/students/13 
* [veryfy] curl -v localhost:8080/students/13 



### Use docker to run database

* [Launch MYSQL in docker] - docker run -p 3306:3306 --name sql_db -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=sample -d mysql
* [MYSQL process ID] - docker ps -aq ==> {some alphanumeric number}
* [Cleanup] - docker stop {some alphanumeric number}
*  docker rm {some alphanumeric number}
