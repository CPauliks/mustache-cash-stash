CREATE TABLE users(
	userName VARCHAR(25) NOT NULL,
	characterCode INT NOT NULL,
	lastKeptAlive DATE);
	
CREATE TABLE requests(
	requesteeName VARCHAR(25) NOT NULL,
	requesteeCode INT NOT NULL,
	requesterName VARCHAR(25) NOT NULL,
	requesterCode INT NOT NULL);
	
CREATE TABLE activeGames(
	gameNum INT NOT NULL,
	XName VARCHAR(25) NOT NULL,
	XCode INT NOT NULL,
	OName VARCHAR(25) NOT NULL,
	OCode INT NOT NULL);
	
VALUES CURRENT_TIME;

INSERT INTO users(userName, characterCode, lastKeptAlive) VALUES('Ben',1,CURRENT_DATE);

UPDATE users SET lastKeptAlive=CURRENT_TIME WHILE userName='Ben'