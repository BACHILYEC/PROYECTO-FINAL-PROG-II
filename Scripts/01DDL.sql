-- database: ../Database/triv.sqlite

DROP TABLE IF EXISTS Answer;
DROP TABLE IF EXISTS Question;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS LeaderBoard;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS UserType;

CREATE TABLE UserType(
    idUserType INTEGER PRIMARY KEY AUTOINCREMENT
    ,Name VARCHAR(15) NOT NULL UNIQUE
    ,Description VARCHAR(50) NOT NULL
    ,Status VARCHAR(10) NOT NULL DEFAULT 'Activo'
    ,CreationDate DATETIME NOT NULL DEFAULT(datetime('now','localtime'))
    ,ModificateDate DATETIME DEFAULT NULL

);

CREATE TABLE Player(
    idPlayer INTEGER PRIMARY KEY AUTOINCREMENT
    ,idUserType INTEGER REFERENCES UserType(idUserType)
    ,Name VARCHAR(50) NOT NULL UNIQUE
    ,Score  INTEGER NOT NULL
    ,Status VARCHAR(10) NOT NULL DEFAULT 'Activo'
    ,CreationDate DATETIME NOT NULL DEFAULT(datetime('now','localtime'))
    ,ModificateDate DATETIME DEFAULT NULL

);

CREATE TABLE Admin(
    idAdmin INTEGER PRIMARY KEY AUTOINCREMENT
    ,idUserType INTEGER REFERENCES UserType(idUserType)
    ,UserName VARCHAR(50) NOT NULL UNIQUE
    ,Password VARCHAR(20) NOT NULL UNIQUE
    ,Status VARCHAR(10) NOT NULL DEFAULT 'Activo'
    ,CreationDate DATETIME NOT NULL DEFAULT(datetime('now','localtime'))
    ,ModificateDate DATETIME DEFAULT NULL

);

CREATE TABLE Category (
    idCategory INTEGER PRIMARY KEY AUTOINCREMENT
    ,Name VARCHAR(50) NOT NULL UNIQUE
    ,Description VARCHAR(100) NOT NULL
    ,Status VARCHAR(10) NOT NULL DEFAULT 'Activo'
    ,CreationDate DATETIME NOT NULL DEFAULT(datetime('now','localtime'))
    ,ModificateDate DATETIME DEFAULT NULL

);

CREATE TABLE LeaderBoard(
    idLeaderBoard INTEGER PRIMARY KEY AUTOINCREMENT
    ,idPlayer INTEGER REFERENCES Player(idPlayer)
    ,idCategory INTEGER REFERENCES Category(idCategory)
    ,Status VARCHAR(10) NOT NULL DEFAULT 'Activo'
    ,CreationDate DATETIME NOT NULL DEFAULT(datetime('now','localtime'))
    ,ModificateDate DATETIME DEFAULT NULL

);

CREATE TABLE Question (
    idCategory INTEGER REFERENCES Category(idCategory)
    ,idQuestion INTEGER PRIMARY KEY AUTOINCREMENT
    ,Question VARCHAR(255) NOT NULL UNIQUE
    ,Status VARCHAR(10) NOT NULL DEFAULT 'Activo'
    ,CreationDate DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,ModificateDate DATETIME DEFAULT NULL
);

CREATE TABLE Answer (
    idQuestion INTEGER REFERENCES Question(idQuestion)
    ,idAnswer INTEGER PRIMARY KEY AUTOINCREMENT
    ,Answer VARCHAR(255) NOT NULL
    ,CorrectAns INTEGER NOT NULL
    ,Status VARCHAR(10) NOT NULL DEFAULT 'Activo'
    ,CreationDate DATETIME NOT NULL DEFAULT(datetime('now','localtime'))
    ,ModificateDate DATETIME DEFAULT NULL
);
