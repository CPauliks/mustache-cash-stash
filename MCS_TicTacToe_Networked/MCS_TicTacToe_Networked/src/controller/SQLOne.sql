CREATE TABLE users(userName VARCHAR(25) NOT NULL,characterCode INT NOT NULL,lastKeptAlive TIMESTAMP);
	
CREATE TABLE requests(requesteeName VARCHAR(25) NOT NULL,requesteeCode INT NOT NULL,requesterName VARCHAR(25) NOT NULL,requesterCode INT NOT NULL);
	
CREATE TABLE activeGames(gameNum INT NOT NULL,XName VARCHAR(25) NOT NULL,XCode INT NOT NULL,OName VARCHAR(25) NOT NULL,OCode INT NOT NULL);