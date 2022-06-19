CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)

);
CREATE TABLE IF NOT EXISTS REPREQUEST (
    repreqid INT PRIMARY KEY auto_increment,
    timestamp TIMESTAMP,
    clientname VARCHAR(20),
    licenceplate VARCHAR(20),
    vinnumber VARCHAR(20),
    defectdescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);

    CREATE TABLE IF NOT EXISTS SCHEDULE(
    scheduleid INT PRIMARY KEY auto_increment,
    mechanic VARCHAR(20),
    beginningtime DATETIME,
    endtime DATETIME,
    repreqid INT,
    foreign key (repreqid) references REPREQUEST (repreqid)
);

    CREATE TABLE IF NOT EXISTS MECHANICS(
    mechid INT PRIMARY KEY auto_increment,
    mechanic VARCHAR (20)
);

    INSERT INTO MECHANICS (mechid, mechanic) VALUES (1,'Olti Béla');
    INSERT INTO MECHANICS (mechid, mechanic) VALUES (2,'Szőcs Domokos');
    INSERT INTO MECHANICS (mechid, mechanic) VALUES (3,'Péter István');
    INSERT INTO MECHANICS (mechid, mechanic) VALUES (4,'Muraru Marin');
    INSERT INTO MECHANICS (mechid, mechanic) VALUES (5,'Tankó Zoltán');
    INSERT INTO MECHANICS (mechid, mechanic) VALUES (6,'Kész Zoltán');