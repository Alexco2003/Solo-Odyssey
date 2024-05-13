CREATE TABLE User (
    id_user INT AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY(id_user)
);

ALTER TABLE User MODIFY username VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin UNIQUE;
ALTER TABLE User MODIFY password VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE Architect (
    id_user INT,
    level INT,
    FOREIGN KEY(id_user) REFERENCES User(id_user) ON DELETE CASCADE,
    PRIMARY KEY(id_user)
);

CREATE TABLE Player (
    id_user INT,
    level INT,
    title VARCHAR(255),
    damage INT,
    health INT,
    money DECIMAL(19,2),
    FOREIGN KEY(id_user) REFERENCES User(id_user) ON DELETE CASCADE,
    PRIMARY KEY(id_user)
);

CREATE TABLE MultiplayerStats (
    id_multiplayer INT AUTO_INCREMENT,
    id_user INT,
    lostMatches INT CHECK (lostMatches >= 0),
    wonMatches INT CHECK (wonMatches >= 0),
    FOREIGN KEY(id_user) REFERENCES User(id_user) ON DELETE CASCADE,
    PRIMARY KEY(id_multiplayer)
);

CREATE TABLE Quest (
    id_quest INT AUTO_INCREMENT,
    id_player INT,
    name VARCHAR(255),
    description TEXT,
    rewardLevel INT,
    rewardMoney DECIMAL(19,2),
    completed BOOLEAN,
    FOREIGN KEY(id_player) REFERENCES Player(id_user) ON DELETE CASCADE,
    PRIMARY KEY(id_quest)
);

CREATE TABLE Shop (
    id_shop INT AUTO_INCREMENT,
    id_player INT,
    name VARCHAR(255),
    FOREIGN KEY(id_player) REFERENCES Player(id_user) ON DELETE CASCADE,
    PRIMARY KEY(id_shop)
);

CREATE TABLE Item (
    id_item INT AUTO_INCREMENT,
    id_shop INT,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(19,2),
    damage INT,
    health INT,
    quantity INT CHECK (quantity >= 0),
    isBought BOOLEAN,
    isStolen BOOLEAN,
    FOREIGN KEY(id_shop) REFERENCES Shop(id_shop) ON DELETE CASCADE,
    PRIMARY KEY(id_item)
);

CREATE TABLE PlayerInventory (
    id_item INT,
    id_player INT,
    quantity INT CHECK (quantity >= 0),
    FOREIGN KEY(id_item) REFERENCES Item(id_item) ON DELETE CASCADE,
    FOREIGN KEY(id_player) REFERENCES Player(id_user) ON DELETE CASCADE,
    PRIMARY KEY(id_item, id_player)
);

CREATE TABLE Dungeon (
    id_dungeon INT AUTO_INCREMENT,
    id_player INT,
    name VARCHAR(255),
    description TEXT,
    level INT,
    rewardLevel INT,
    rewardMoney DECIMAL(19,2),
    completed BOOLEAN,
    FOREIGN KEY(id_player) REFERENCES Player(id_user) ON DELETE CASCADE,
    PRIMARY KEY(id_dungeon)
);

CREATE TABLE Enemy (
    id_enemy INT AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    health INT,
    damage INT,
    encountered BOOLEAN,
    PRIMARY KEY(id_enemy)
);

CREATE TABLE DungeonEnemy (
    id_dungeon INT,
    id_enemy INT,
    FOREIGN KEY(id_dungeon) REFERENCES Dungeon(id_dungeon) ON DELETE CASCADE,
    FOREIGN KEY(id_enemy) REFERENCES Enemy(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_dungeon, id_enemy)
);

CREATE TABLE Tank (
    id_enemy INT,
    armor INT,
    FOREIGN KEY(id_enemy) REFERENCES Enemy(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_enemy)
);

CREATE TABLE Assassin (
    id_enemy INT,
    criticalChance INT,
    FOREIGN KEY(id_enemy) REFERENCES Enemy(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_enemy)
);

CREATE TABLE Mage (
    id_enemy INT,
    mana INT,
    FOREIGN KEY(id_enemy) REFERENCES Enemy(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_enemy)
);

CREATE TABLE BossTank (
    id_enemy INT,
    FOREIGN KEY(id_enemy) REFERENCES Tank(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_enemy)
);

CREATE TABLE BossMage (
    id_enemy INT,
    FOREIGN KEY(id_enemy) REFERENCES Mage(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_enemy)
);

CREATE TABLE BossAssassin (
    id_enemy INT,
    FOREIGN KEY(id_enemy) REFERENCES Assassin(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_enemy)
);


CREATE TABLE BossTankInventory (
    id_item INT,
    id_enemy INT,
    FOREIGN KEY(id_item) REFERENCES Item(id_item) ON DELETE CASCADE,
    FOREIGN KEY(id_enemy) REFERENCES BossTank(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_item, id_enemy)
);

CREATE TABLE BossMageInventory (
    id_item INT,
    id_enemy INT,
    FOREIGN KEY(id_item) REFERENCES Item(id_item) ON DELETE CASCADE,
    FOREIGN KEY(id_enemy) REFERENCES BossMage(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_item, id_enemy)
);

CREATE TABLE BossAssassinInventory (
    id_item INT,
    id_enemy INT,
    FOREIGN KEY(id_item) REFERENCES Item(id_item) ON DELETE CASCADE,
    FOREIGN KEY(id_enemy) REFERENCES BossAssassin(id_enemy) ON DELETE CASCADE,
    PRIMARY KEY(id_item, id_enemy)
);

DROP TABLE User;
DROP TABLE Architect;
DROP TABLE Player;
DROP TABLE MultiplayerStats;
DROP TABLE Quest;
DROP TABLE Shop;
DROP TABLE Item;
DROP TABLE PlayerInventory;
DROP TABLE Dungeon;
DROP TABLE Enemy;
DROP TABLE DungeonEnemy;
DROP TABLE Tank;
DROP TABLE Assassin;
DROP TABLE Mage;
DROP TABLE BossTank;
DROP TABLE BossMage;
DROP TABLE BossAssassin;
DROP TABLE BossTankInventory;
DROP TABLE BossMageInventory;
DROP TABLE BossAssassinInventory;

DELETE FROM User;
DELETE FROM Player;
DELETE FROM MultiplayerStats;
DELETE FROM Architect;
DELETE FROM Quest;
DELETE FROM Shop;
DELETE FROM Item;
DELETE FROM PlayerInventory;
DELETE FROM Dungeon;
DELETE FROM Enemy;
DELETE FROM DungeonEnemy;
DELETE FROM Tank;
DELETE FROM Assassin;
DELETE FROM Mage;
DELETE FROM BossTank;
DELETE FROM BossMage;
DELETE FROM BossAssassin;
DELETE FROM BossTankInventory;
DELETE FROM BossMageInventory;
DELETE FROM BossAssassinInventory;

SELECT * FROM User;
SELECT * FROM Architect;
SELECT * FROM Player;
SELECT * FROM MultiplayerStats;
SELECT * FROM Quest;
SELECT * FROM Shop;
SELECT * FROM Item;
SELECT * FROM PlayerInventory;
SELECT * FROM Dungeon;
SELECT * FROM Enemy;
SELECT * FROM DungeonEnemy;
SELECT * FROM Tank;
SELECT * FROM Assassin;
SELECT * FROM Mage;
SELECT * FROM BossTank;
SELECT * FROM BossMage;
SELECT * FROM BossAssassin;
SELECT * FROM BossTankInventory;
SELECT * FROM BossMageInventory;
SELECT * FROM BossAssassinInventory;