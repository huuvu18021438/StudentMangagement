CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  name VARCHAR (100) NOT NULL,
  birthday DATE NOT NULL,
  phone VARCHAR (15) NOT NULL,
  email VARCHAR (50) NOT NULL,
  address_id INTEGER NOT NULL,
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE course (
 id SERIAL PRIMARY KEY,
 name VARCHAR (100) NOT NULL,
 credits INTEGER NOT NULL,
 description VARCHAR (3000) NOT NULL,
 teacher_id INTEGER NOT NULL,
 created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE teacher (
 id SERIAL PRIMARY KEY,
 name VARCHAR (100) NOT NULL,
 teacher_type VARCHAR (50) NOT NULL,
 created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 );

 CREATE TABLE address (
 id SERIAL PRIMARY KEY,
 country VARCHAR (50) NOT NULL,
 city VARCHAR (50) NOT NULL,
 street VARCHAR (50) NOT NULL,
 created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 );

 CREATE TABLE student_course (
 id SERIAL PRIMARY KEY,
 student_id INTEGER REFERENCES student(id),
 course_id INTEGER REFERENCES course(id)
 );

ALTER TABLE student
ADD CONSTRAINT fk_student_address
FOREIGN KEY (address_id)
REFERENCES address (id);

ALTER TABLE course
ADD CONSTRAINT fk_course_teacher
FOREIGN KEY (teacher_id)
REFERENCES teacher (id);