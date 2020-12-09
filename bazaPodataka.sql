CREATE DATABASE fifaranglist;
CREATE TABLE `fifaranglist`.`user_table` ( `id` INT NOT NULL AUTO_INCREMENT, `username` VARCHAR(50) UNIQUE, `password` VARCHAR(100), `active` BOOL, `administrator` BOOL, PRIMARY KEY (`id`) ); 
CREATE TABLE `fifaranglist`.`confederation` ( `id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(50) UNIQUE, `strenght` DOUBLE, PRIMARY KEY (`id`) ); 
CREATE TABLE `fifaranglist`.`matchType` ( `id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(50) UNIQUE, `strenght` DOUBLE NOT NULL, PRIMARY KEY (`id`) ); 
CREATE TABLE `fifaranglist`.`selection` ( `id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(50) UNIQUE, `confederation` INT, `active` BOOL, `points` INT, `rang` INT, `userID` INT, PRIMARY KEY (`id`), FOREIGN KEY (`userID`) REFERENCES `fifaranglist`.`user_table`(`id`) ON UPDATE CASCADE ON DELETE RESTRICT, FOREIGN KEY (`confederation`) REFERENCES `fifaranglist`.`confederation`(`id`) ON UPDATE CASCADE ON DELETE RESTRICT );
CREATE TABLE `fifaranglist`.`match_table` ( `id` INT NOT NULL AUTO_INCREMENT, `host` INT, `away` INT, `hostGoals` INT, `awayGoals` INT, `matchType` INT, `date` DATE, `userID` INT, PRIMARY KEY (`id`), FOREIGN KEY (`userID`) REFERENCES `fifaranglist`.`user_table`(`id`) ON UPDATE CASCADE ON DELETE RESTRICT, FOREIGN KEY (`away`) REFERENCES `fifaranglist`.`selection`(`id`) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (`host`) REFERENCES `fifaranglist`.`selection`(`id`) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (`matchType`) REFERENCES `fifaranglist`.`matchtype`(`id`) ON UPDATE CASCADE ON DELETE RESTRICT ); 


/*+ Password je administrator */
INSERT INTO fifaranglist.user_table(username,PASSWORD,ACTIVE,administrator) VALUE ('administrator','200ceb26807d6bf99fd6f4f0d1ca54d4',TRUE,TRUE);

INSERT INTO fifaranglist.confederation(NAME,strenght) VALUES ("EUROPE",1);
INSERT INTO fifaranglist.confederation(NAME,strenght) VALUES ("SOUTH AMERICA",1);
INSERT INTO fifaranglist.confederation(NAME,strenght) VALUES ("NORTH AMERICA",0.88);
INSERT INTO fifaranglist.confederation(NAME,strenght) VALUES ("ASIA",0.86);
INSERT INTO fifaranglist.confederation(NAME,strenght) VALUES ("AFRICA",0.86);
INSERT INTO fifaranglist.confederation(NAME,strenght) VALUES ("OCENIA",0.86);

INSERT INTO fifaranglist.matchtype(NAME,strenght) VALUES ("FRIENDLY",1);
INSERT INTO fifaranglist.matchtype(NAME,strenght) VALUES ("WORLD CUP QUALIFIER",2.5);
INSERT INTO fifaranglist.matchtype(NAME,strenght) VALUES ("CONFEDERATION CUP",3);
INSERT INTO fifaranglist.matchtype(NAME,strenght) VALUES ("WORLD CUP",4);

INSERT INTO fifaranglist.selection (NAME,confederation,points,rang,userID,ACTIVE) VALUES('Argentina',2,0,1,1,1);
INSERT INTO fifaranglist.selection (NAME,confederation,points,rang,userID,ACTIVE) VALUES('Brasil',2,0,2,1,1);
INSERT INTO fifaranglist.selection (NAME,confederation,points,rang,userID,ACTIVE) VALUES('Serbia',1,0,3,1,1);
INSERT INTO fifaranglist.selection (NAME,confederation,points,rang,userID,ACTIVE) VALUES('Belgium',1,0,4,1,1);
INSERT INTO fifaranglist.selection (NAME,confederation,points,rang,userID,ACTIVE) VALUES('Australia',6,0,5,1,1);
INSERT INTO fifaranglist.selection (NAME,confederation,points,rang,userID,ACTIVE) VALUES('Nigeria',5,0,6,1,1);
INSERT INTO fifaranglist.selection (NAME,confederation,points,rang,userID,ACTIVE) VALUES('Yugoslavia',1,0,7,1,0);

INSERT INTO fifaranglist.match_table(HOST,away,hostGoals,awayGoals,DATE,matchtype,userID) VALUES  (5,6,2,2,'2020-01-01',1,1);
INSERT INTO fifaranglist.match_table(HOST,away,hostGoals,awayGoals,DATE,matchtype,userID) VALUES  (1,2,1,2,'2019-03-05',2,1);
INSERT INTO fifaranglist.match_table(HOST,away,hostGoals,awayGoals,DATE,matchtype,userID) VALUES  (3,4,0,0,'2018-03-07',2,1);
INSERT INTO fifaranglist.match_table(HOST,away,hostGoals,awayGoals,DATE,matchtype,userID) VALUES  (6,2,3,1,'2020-03-02',3,1);



