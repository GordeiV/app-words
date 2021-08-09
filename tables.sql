USE wordsapp;

DROP TABLE IF EXISTS words;
DROP TABLE IF EXISTS vocabulary;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id_user SERIAL,
    login varchar(100) unique,
    u_password varchar(255),
    PRIMARY KEY(id_user)
);

CREATE TABLE vocabulary (
	id_vocabulary SERIAL,
    v_name varchar(50),
    v_date DATETIME,
    id_user BIGINT unsigned not null,
    PRIMARY KEY(id_vocabulary),
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE
);


CREATE TABLE words (
	id_word SERIAL,
    foreign_word varchar(45) not null,
    native_word varchar(45) not null,
    transcription varchar(45),
    id_vocabulary BIGINT unsigned not null,
    PRIMARY KEY(id_word),
    FOREIGN KEY(id_vocabulary) REFERENCES vocabulary(id_vocabulary) ON DELETE CASCADE
);