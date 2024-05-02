USE address;

CREATE TABLE T_Users (
	username	varchar(30)	PRIMARY KEY,
	password	varchar(400),
	active      boolean
) ENGINE = InnoDB;

INSERT INTO T_Users (username, password, active)
VALUES
("Manex", "$2a$12$wA5BMG3bCouAI8evmD6pFeAbaYIJfua./nvr8nBqPZamJ56UqPF4W", true), --max12345
("Arthur", "$2a$12$3Vu3P9cUL6kEdmAk1.SM1OFk8O3ea9RbzsmA.6XEkP6jBmDCJvAZy", true), --leeroy12
("Claire", "$2a$12$NFkoMUuyzmBMt.LyOXiRAuEImtHnFiGAZMjnFPq4YxXNlH0AZm0fW", true); --claire12
SELECT * FROM T_Users;

CREATE TABLE T_Roles (
	role		varchar(25)		PRIMARY KEY
)ENGINE = InnoDB;

INSERT INTO T_Roles (role)
VALUES
("USER"),
("ADMIN");

CREATE TABLE T_Users_Roles (
	username	varchar(30),
	role		varchar(25),
	PRIMARY KEY(username,role)
)ENGINE = InnoDB;

INSERT INTO T_Users_Roles (username,role)
VALUES
("Manex","USER"),
("Manex","ADMIN"),
("Arthur","USER"),
("Claire","USER");
