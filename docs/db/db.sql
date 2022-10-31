CREATE TABLE persone
(
   id integer PRIMARY KEY AUTOINCREMENT,
   role_id integer NOT NULL REFERENCES role (id),
   first name varchar NOT NULL,
   second name varchar NOT NULL,
   patronymic varchar NOT NULL,
   mail varchar NOT NULL UNIQUE
);
CREATE TABLE faculty
(
   id integer PRIMARY KEY AUTOINCREMENT,
   name varchar NOT NULL
);
CREATE TABLE role
(
   id integer PRIMARY KEY AUTOINCREMENT,
   name varchar NOT NULL
);
CREATE TABLE subject
(
   id integer PRIMARY KEY AUTOINCREMENT,
   subject_name varchar NOT NULL
);
CREATE TABLE state
(
   id integer PRIMARY KEY AUTOINCREMENT,
   state_name varchar NOT NULL
);
CREATE TABLE request
(
   id integer PRIMARY KEY AUTOINCREMENT,
   person_id integer NOT NULL REFERENCES persone(id),
   speciality_id integer NOT NULL REFERENCES speciality(id),
   state_id integer NOT NULL REFERENCES state(id)
);
CREATE TABLE speciality
(
   id integer PRIMARY KEY AUTOINCREMENT,
   first_subject_id integer NOT NULL REFERENCES subject(id),
   second_subject_id integer NOT NULL REFERENCES subject(id),
   third_subject_id integer NOT NULL REFERENCES subject(id),
   name varchar NOT NULL ,
   faculty_id NOT NULL REFERENCES faculty(id)
);