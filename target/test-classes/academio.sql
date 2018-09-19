create schema if not exists academio;

CREATE TABLE courses (
  id int(6) NOT NULL,
  is_active bit(1) NOT NULL,
  title varchar(30) NOT NULL,
  hours int(6) NOT NULL,
  course_level int(6) NOT NULL,
  teacher int(6) NOT NULL,
  agenda_file_name varchar(300) DEFAULT NULL
);

CREATE TABLE course_levels (
  id int(6) NOT NULL,
  name varchar(30) NOT NULL
);

INSERT INTO course_levels (id, name) VALUES
(1, 'basic'),
(2, 'medium'),
(3, 'advanced');

CREATE TABLE teachers (
  id int(6) NOT NULL,
  name varchar(30) NOT NULL
);

INSERT INTO teachers (id, name) VALUES
(1, 'John Doe'),
(2, 'Jane Doe'),
(3, 'Peter Doug');

ALTER TABLE courses
  ADD PRIMARY KEY (id);

ALTER TABLE course_levels
  ADD PRIMARY KEY (id);

ALTER TABLE teachers
  ADD PRIMARY KEY (id);

ALTER TABLE courses
  MODIFY id int(6) NOT NULL AUTO_INCREMENT;

ALTER TABLE course_levels
  MODIFY id int(6) NOT NULL AUTO_INCREMENT;

ALTER TABLE teachers
  MODIFY id int(6) NOT NULL AUTO_INCREMENT;

ALTER TABLE courses ADD CONSTRAINT courses_ibfk_1 FOREIGN KEY (teacher) REFERENCES teachers (id);
ALTER TABLE courses ADD CONSTRAINT courses_ibfk_2 FOREIGN KEY (course_level) REFERENCES course_levels (id);

insert into courses values(1, 1, 'test', 125, 1, 1, 'test');
