CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS MOVIES (
    movieid INT PRIMARY KEY auto_increment,
    movietitle VARCHAR(20),
    moviedescription VARCHAR (1000),
    userid INT,
    insertdate DATE,
    foreign key (userid) references USERS(userid)

);

CREATE TABLE IF NOT EXISTS LIKES (
    likeid INT PRIMARY KEY auto_increment,
    movieid INT,
    userid INT,
    foreign key (userid) references USERS(userid),
    foreign key (movieid) references MOVIES(movieid)
);

CREATE TABLE IF NOT EXISTS HATES (
    hateid INT PRIMARY KEY auto_increment,
    movieid INT,
    userid INT,
    foreign key (userid) references USERS(userid),
    foreign key (movieid) references MOVIES(movieid)
);