CREATE TABLE User (
    id_user INT AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY(id_user)
);

CREATE TABLE Architect (
    id_user INT,
    level INT,
    FOREIGN KEY(id_user) REFERENCES User(id_user),
    PRIMARY KEY(id_user)
);

CREATE TABLE Player (
    id_user INT,
    level INT,
    title VARCHAR(255),
    damage INT,
    health INT,
    money DECIMAL(19,2),
    FOREIGN KEY(id_user) REFERENCES User(id_user),
    PRIMARY KEY(id_user)
);

CREATE TABLE Quest (
    id_quest INT AUTO_INCREMENT,
    id_player INT,
    name VARCHAR(255),
    description TEXT,
    rewardLevel INT,
    rewardMoney DECIMAL(19,2),
    completed BOOLEAN,
    FOREIGN KEY(id_player) REFERENCES Player(id_user),
    PRIMARY KEY(id_quest)
);

CREATE TABLE Shop (
    id_shop INT AUTO_INCREMENT,
    id_player INT,
    name VARCHAR(255),
    FOREIGN KEY(id_player) REFERENCES Player(id_user),
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
    isBought BOOLEAN,
    FOREIGN KEY(id_shop) REFERENCES Shop(id_shop),
    PRIMARY KEY(id_item)
);

CREATE TABLE PlayerInventory (
    id_item INT,
    id_player INT,
    FOREIGN KEY(id_item) REFERENCES Item(id_item),
    FOREIGN KEY(id_player) REFERENCES Player(id_user),
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
    FOREIGN KEY(id_player) REFERENCES Player(id_user),
    PRIMARY KEY(id_dungeon)
);

CREATE TABLE Enemy (
    id_enemy INT AUTO_INCREMENT,
    id_dungeon INT,
    name VARCHAR(255),
    description TEXT,
    health INT,
    damage INT,
    FOREIGN KEY(id_dungeon) REFERENCES Dungeon(id_dungeon),
    PRIMARY KEY(id_enemy)
);

CREATE TABLE Tank (
    id_enemy INT,
    armor INT,
    FOREIGN KEY(id_enemy) REFERENCES Enemy(id_enemy),
    PRIMARY KEY(id_enemy)
);

CREATE TABLE Assassin (
    id_enemy INT,
    criticalChance INT,
    FOREIGN KEY(id_enemy) REFERENCES Enemy(id_enemy),
    PRIMARY KEY(id_enemy)
);

CREATE TABLE Mage (
    id_enemy INT,
    mana INT,
    FOREIGN KEY(id_enemy) REFERENCES Enemy(id_enemy),
    PRIMARY KEY(id_enemy)
);

CREATE TABLE BossTank (
    id_enemy INT,
    FOREIGN KEY(id_enemy) REFERENCES Tank(id_enemy),
    PRIMARY KEY(id_enemy)
);

CREATE TABLE BossMage (
    id_enemy INT,
    FOREIGN KEY(id_enemy) REFERENCES Mage(id_enemy),
    PRIMARY KEY(id_enemy)
);

CREATE TABLE BossAssassin (
    id_enemy INT,
    FOREIGN KEY(id_enemy) REFERENCES Assassin(id_enemy),
    PRIMARY KEY(id_enemy)
);


CREATE TABLE BossTankInventory (
    id_item INT,
    id_enemy INT,
    FOREIGN KEY(id_item) REFERENCES Item(id_item),
    FOREIGN KEY(id_enemy) REFERENCES BossTank(id_enemy),
    PRIMARY KEY(id_item, id_enemy)
);

CREATE TABLE BossMageInventory (
    id_item INT,
    id_enemy INT,
    FOREIGN KEY(id_item) REFERENCES Item(id_item),
    FOREIGN KEY(id_enemy) REFERENCES BossMage(id_enemy),
    PRIMARY KEY(id_item, id_enemy)
);

CREATE TABLE BossAssassinInventory (
    id_item INT,
    id_enemy INT,
    FOREIGN KEY(id_item) REFERENCES Item(id_item),
    FOREIGN KEY(id_enemy) REFERENCES BossAssassin(id_enemy),
    PRIMARY KEY(id_item, id_enemy)
);

DROP TABLE User;
DROP TABLE Architect;
DROP TABLE Player;
DROP TABLE Quest;
DROP TABLE Shop;
DROP TABLE Item;
DROP TABLE PlayerInventory;
DROP TABLE Dungeon;
DROP TABLE Enemy;
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
DELETE FROM Architect;
DELETE FROM Quest;
DELETE FROM Shop;
DELETE FROM Item;
DELETE FROM PlayerInventory;
DELETE FROM Dungeon;
DELETE FROM Enemy;
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
SELECT * FROM Quest;
SELECT * FROM Shop;
SELECT * FROM Item;
SELECT * FROM PlayerInventory;
SELECT * FROM Dungeon;
SELECT * FROM Enemy;
SELECT * FROM Tank;
SELECT * FROM Assassin;
SELECT * FROM Mage;
SELECT * FROM BossTank;
SELECT * FROM BossMage;
SELECT * FROM BossAssassin;
SELECT * FROM BossTankInventory;
SELECT * FROM BossMageInventory;
SELECT * FROM BossAssassinInventory;


CREATE TRIGGER after_insert_player
AFTER INSERT
ON Player
FOR EACH ROW
BEGIN
    DECLARE new_id_shop INT;

    --Shop
    INSERT INTO Shop(id_player, name) VALUES (NEW.id_user, 'The Architect''s Emporium');

    --Quests

    -- Quests requiring specific actions or tasks
    INSERT INTO Quest (id_player, name, description, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user, 'The Lost Key', 'Find the hidden key to unlock the treasure chest.', 2, 150.50, false),
        (NEW.id_user, 'The Enigmatic Riddle', 'Solve the riddle to uncover the hidden passage.', 4, 60.60, false),
        (NEW.id_user, 'The Great Escape', 'Navigate through the labyrinth and reach the exit.', 3, 200.25, false),
        (NEW.id_user, 'The Button Press Challenge', 'Press the button space 100 times within a minute.', 5, 175.50, false),
        (NEW.id_user, 'The Timing Test', 'Press the button X when the clock strikes midnight.', 1, 80.00, false),
        (NEW.id_user, 'The Logic Puzzle', 'Solve the match calculus question to reveal the hidden code.', 7, 60.00, false),
        (NEW.id_user, 'The Maze Runner', 'Navigate the maze and find the hidden treasure.', 10, 110.40, false),
        (NEW.id_user, 'The Crystal Collection', 'Collect 10 crystals scattered across the land.', 8, 85.00, false),
        (NEW.id_user, 'The Time Traveler', 'Travel through time and return with evidence of your journey.', 9, 90.00, false),
        (NEW.id_user, 'The Ancient Relic', 'Retrieve the ancient relic guarded by mythical creatures.', 6, 100.00, false);

    -- Dungeon defeat quests
    INSERT INTO Quest (id_player, name, description, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user, 'Defeated 1 Dungeon', 'Successfully defeat 1 dungeon.', 5, 200.00, false),
        (NEW.id_user, 'Defeated 2 Dungeons', 'Successfully defeat 2 dungeons.', 10, 400.00, false),
        (NEW.id_user, 'Defeated 3 Dungeons', 'Successfully defeat 3 dungeons.', 15, 600.00, false),
        (NEW.id_user, 'Defeated 5 Dungeons', 'Successfully defeat 5 dungeons.', 20, 800.00, false),
        (NEW.id_user, 'Defeated 10 Dungeons', 'Successfully defeat 10 dungeons.', 25, 1000.00, false);

    -- Secret endgame quest
    INSERT INTO Quest (id_player, name, description, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user, 'The Final Confrontation', 'Details will be revealed soon...', 100, 10000.00, false);

    -- PVP quests
    INSERT INTO Quest (id_player, name, description, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user, 'Defeated 1 Player', 'Successfully defeat 1 player in PVP.', 20, 250.00, false),
        (NEW.id_user, 'Defeated 2 Players', 'Successfully defeat 2 players in PVP.', 25, 500.00, false),
        (NEW.id_user, 'Defeated 3 Players', 'Successfully defeat 3 players in PVP.', 30, 750.00, false),
        (NEW.id_user, 'Defeated 4 Players', 'Successfully defeat 4 players in PVP.', 40, 1000.00, false),
        (NEW.id_user, 'Defeated 5 Players', 'Successfully defeat 5 players in PVP.', 50, 1250.00, false);

    --Get the id of the player's shop
    SELECT id_shop INTO new_id_shop FROM Shop WHERE id_player = NEW.id_user;

    --Items
    INSERT INTO Item (id_shop, name, description, price, damage, health, isBought)
    VALUES
        (new_id_shop, 'Wooden Sword', 'A basic wooden sword.', 10.50, 10, 5, false),
        (new_id_shop, 'Health Potion', 'Boosts health.', 20.25, 0, 15, false),
        (new_id_shop, 'Iron Armor', 'Protects against damage.', 30.75, 0, 25, false),
        (new_id_shop, 'Magic Wand', 'A wand that enhances magic abilities.', 40.80, 20, 5, false),
        (new_id_shop, 'Speed Boots', 'Increases movement speed.', 50.60, 0, 5, false),
        (new_id_shop, 'Fireball Scroll', 'Unleashes a powerful fireball.', 60.95, 30, 0, false),
        (new_id_shop, 'Ice Shield', 'Creates a shield of ice.', 70.35, 0, 20, false),
        (new_id_shop, 'Poison Dagger', 'Inflicts poison damage.', 80.20, 15, 5, false),
        (new_id_shop, 'Thunder Staff', 'Summons lightning bolts.', 90.45, 25, 5, false),
        (new_id_shop, 'Healing Staff', 'Restores health to allies.', 100.15, 0, 30, false),
        (new_id_shop, 'Shadow Cloak', 'Grants invisibility.', 110.70, 0, 5, false),
        (new_id_shop, 'Soul Gem', 'Absorbs enemy souls.', 120.90, 30, 5, false),
        (new_id_shop, 'Dragon Scale Armor', 'Legendary armor.', 130.80, 0, 40, false),
        (new_id_shop, 'Phoenix Feather', 'Blesses the player.', 140.45, 0, 10, false),
        (new_id_shop, 'Elixir of Immortality', 'Grants eternal life.', 150.30, 0, 10, false),
        (new_id_shop, 'Ancient Rune', 'Unleashes ancient powers.', 160.70, 40, 20, false),
        (new_id_shop, 'Celestial Bow', 'Shoots arrows of light.', 170.60, 35, 5, false),
        (new_id_shop, 'Vampire Fang', 'Drains enemy health.', 180.25, 20, 5, false),
        (new_id_shop, 'Titan Gauntlet', 'Crushes enemies with immense strength.', 190.85, 50, 5, false),
        (new_id_shop, 'Mystic Orb', 'Manipulates the elements.', 200.75, 45, 25, false),
        (new_id_shop, 'Golden Apple', 'Grants permanent health boost.', 210.90, 0, 5, false),
        (new_id_shop, 'Divine Sword', 'A sword blessed by the gods.', 220.55, 60, 5, false),
        (new_id_shop, 'Crystal Staff', 'Channels crystal energy.', 230.35, 55, 5, false),
        (new_id_shop, 'Shadow Amulet', 'Conceals the user in darkness.', 240.20, 0, 30, false),
        (new_id_shop, 'Soul Reaper Scythe', 'Harvests enemy souls.', 250.45, 65, 5, false),
        (new_id_shop, 'Dragonfire Shield', 'Blocks dragonfire attacks.', 260.95, 0, 35, false),
        (new_id_shop, 'Phoenix Tear', 'Revives the soul of the Phoenix.', 270.30, 0, 15, false),
        (new_id_shop, 'Elixir of Ascension', 'Ascends the user to a higher plane.', 280.80, 0, 15, false),
        (new_id_shop, 'Ancient Scroll', 'Unlocks ancient knowledge.', 290.75, 70, 10, false),
        (new_id_shop, 'Celestial Staff', 'Channels celestial energy.', 300.60, 75, 5, false),
        (new_id_shop, 'Vampire Cloak', 'Drains enemy health over time.', 310.25, 25, 5, false),
        (new_id_shop, 'Titan Hammer', 'Crushes enemies with divine power.', 320.85, 80, 5, false),
        (new_id_shop, 'Mystic Crystal', 'Harnesses the power of crystals.', 330.70, 85, 5, false),
        (new_id_shop, 'Golden Shield', 'Grants permanent armor boost.', 340.90, 0, 500, false),
        (new_id_shop, 'Divine Bow', 'Shoots arrows of divine light.', 350.55, 80, 5, false),
        (new_id_shop, 'Crystal Orb', 'Manipulates crystal elements.', 360.35, 90, 5, false),
        (new_id_shop, 'Shadow Cloak', 'Conceals the user in shadow.', 370.20, 0, 5, false),

        --Boss items

        --Assassin
        (new_id_shop, 'Vorpal Shadowblade''s Helmet', 'A helm worn by the Vorpal Shadowblade.', 200.00, 150, 0, false),
        (new_id_shop, 'Vorpal Shadowblade''s Armor', 'Protective armor worn by the Vorpal Shadowblade.', 300.00, 50, 100, false),
        (new_id_shop, 'Vorpal Shadowblade''s Glove', 'A glove used by the Vorpal Shadowblade.', 150.00, 350, 0, false),
        (new_id_shop, 'Vorpal Shadowblade''s Shoe', 'A shoe worn by the Vorpal Shadowblade.', 150.00, 0, 40, false),
        (new_id_shop, 'Vorpal Shadowblade''s Ring', 'A ring imbued with the power of the Vorpal Shadowblade.', 250.00, 200, 20, false),
        (new_id_shop, 'Vorpal Shadowblade''s Necklace', 'A necklace worn by the Vorpal Shadowblade.', 250.00, 150, 25, false),

        --Tank
        (new_id_shop, 'Colossus Steelhide''s Helmet', 'A massive helmet forged from steel.', 150.00, 0, 100, false),
        (new_id_shop, 'Colossus Steelhide''s Armor', 'Thick armor made from the toughest steel.', 250.00, 0, 200, false),
        (new_id_shop, 'Colossus Steelhide''s Gauntlets', 'Heavy gauntlets that pack a punch.', 200.00, 50, 100, false),
        (new_id_shop, 'Colossus Steelhide''s Boots', 'Boots designed for stomping enemies.', 200.00, 100, 50, false),
        (new_id_shop, 'Colossus Steelhide''s Ring', 'A ring imbued with protective magic.', 300.00, 0, 300, false),
        (new_id_shop, 'Colossus Steelhide''s Necklace', 'A necklace that enhances endurance.', 300.00, 0, 300, false),

        --Mage
        (new_id_shop, 'Ignis Pyreborn''s Helm', 'A helm infused with the essence of fire.', 200.00, 80, 80, false),
        (new_id_shop, 'Ignis Pyreborn''s Robe', 'A robe woven from flames.', 300.00, 40, 120, false),
        (new_id_shop, 'Ignis Pyreborn''s Bracers', 'Bracers that channel fiery magic.', 150.00, 60, 60, false),
        (new_id_shop, 'Ignis Pyreborn''s Boots', 'Boots that leave behind trails of fire.', 150.00, 20, 100, false),
        (new_id_shop, 'Ignis Pyreborn''s Ring', 'A ring forged in the heart of a volcano.', 250.00, 100, 50, false),
        (new_id_shop, 'Ignis Pyreborn''s Amulet', 'An amulet pulsating with fiery energy.', 250.00, 80, 80, false),

        --Assassin
        (new_id_shop, 'Frostbite''s Frostfang', 'A dagger forged from enchanted ice.', 200.00, 180, 0, false),
        (new_id_shop, 'Frostbite''s Frostcloak', 'A cloak that freezes the air around it.', 300.00, 0, 250, false),
        (new_id_shop, 'Frostbite''s Frostblade', 'A blade as cold as the heart of winter.', 150.00, 250, 0, false),
        (new_id_shop, 'Frostbite''s Frostboots', 'Boots that leave frozen footprints in their wake.', 150.00, 0, 180, false),
        (new_id_shop, 'Frostbite''s Frostband', 'A band that channels the chill of the tundra.', 250.00, 220, 0, false),
        (new_id_shop, 'Frostbite''s Frostpendant', 'A pendant that emanates an icy aura.', 250.00, 0, 200, false),

        --Mage
        (new_id_shop, 'Eldritch Spellbinder''s Helm', 'A mystical helmet imbued with eldritch power.', 150.00, 10, 20, false),
        (new_id_shop, 'Eldritch Spellbinder''s Armor', 'Enchanted armor that harnesses eldritch energy.', 250.00, 15, 25, false),
        (new_id_shop, 'Eldritch Spellbinder''s Gauntlets', 'Magical gauntlets that enhance spellcasting.', 200.00, 20, 15, false),
        (new_id_shop, 'Eldritch Spellbinder''s Boots', 'Boots infused with otherworldly agility.', 180.00, 15, 20, false),
        (new_id_shop, 'Eldritch Spellbinder''s Ring', 'A ring pulsating with eldritch power.', 300.00, 10, 30, false),
        (new_id_shop, 'Eldritch Spellbinder''s Necklace', 'A mystical necklace that boosts magical prowess.', 350.00, 20, 15, false),

         --Tank
        (new_id_shop, 'Leviathan''s Helm', 'A helm crafted from Leviathan scales.', 150.00, 0, 200, false),
        (new_id_shop, 'Leviathan''s Shell', 'A shell capable of withstanding immense pressure.', 250.00, 0, 300, false),
        (new_id_shop, 'Leviathan''s Gauntlets', 'Gauntlets forged from hardened coral.', 200.00, 0, 250, false),
        (new_id_shop, 'Leviathan''s Greaves', 'Greaves reinforced with underwater alloys.', 200.00, 0, 200, false),
        (new_id_shop, 'Leviathan''s Ring', 'A ring infused with the essence of the ocean depths.', 300.00, 0, 350, false),
        (new_id_shop, 'Leviathan''s Amulet', 'An amulet that grants dominion over the seas.', 300.00, 0, 300, false),

        --Mage
        (new_id_shop, 'Celestial Archon''s Circlet', 'A circlet adorned with celestial gemstones.', 200.00, 80, 80, false),
        (new_id_shop, 'Celestial Archon''s Robes', 'Robes infused with celestial energy.', 300.00, 50, 100, false),
        (new_id_shop, 'Celestial Archon''s Gloves', 'Gloves that channel divine magic.', 150.00, 100, 50, false),
        (new_id_shop, 'Celestial Archon''s Sandals', 'Sandals that grant ethereal mobility.', 150.00, 0, 100, false),
        (new_id_shop, 'Celestial Archon''s Ring', 'A ring blessed by celestial beings.', 250.00, 50, 100, false),
        (new_id_shop, 'Celestial Archon''s Pendant', 'A pendant imbued with celestial power.', 250.00, 100, 50, false),

        --Assassin
        (new_id_shop, 'Infernal Revenant''s Mask', 'A mask veiled in flames.', 200.00, 150, 0, false),
        (new_id_shop, 'Infernal Revenant''s Cloak', 'A cloak engulfed in infernal fire.', 300.00, 0, 200, false),
        (new_id_shop, 'Infernal Revenant''s Blades', 'Blades forged from hellfire.', 150.00, 200, 0, false),
        (new_id_shop, 'Infernal Revenant''s Boots', 'Boots that leave trails of fire.', 150.00, 0, 150, false),
        (new_id_shop, 'Infernal Revenant''s Ring', 'A ring infused with infernal power.', 250.00, 200, 0, false),
        (new_id_shop, 'Infernal Revenant''s Amulet', 'An amulet that harnesses hellfire.', 250.00, 0, 200, false),

        --Tank
        (new_id_shop, 'Astral Overlord''s Helmet', 'A helm worn by the Astral Overlord.', 200.00, 150, 0, false),
        (new_id_shop, 'Astral Overlord''s Armor', 'Protective armor worn by the Astral Overlord.', 300.00, 50, 100, false),
        (new_id_shop, 'Astral Overlord''s Glove', 'A glove used by the Astral Overlord.', 150.00, 350, 0, false),
        (new_id_shop, 'Astral Overlord''s Shoe', 'A shoe worn by the Astral Overlord.', 150.00, 0, 40, false),
        (new_id_shop, 'Astral Overlord''s Ring', 'A ring imbued with the power of the Astral Overlord.', 250.00, 200, 20, false),
        (new_id_shop, 'Astral Overlord''s Necklace', 'A necklace worn by the Astral Overlord.', 250.00, 150, 25, false),

        --Mage
        (new_id_shop, 'Void Phantom''s Helmet', 'A helm worn by the Void Phantom.', 200.00, 150, 0, false),
        (new_id_shop, 'Void Phantom''s Armor', 'Protective armor worn by the Void Phantom.', 300.00, 50, 100, false),
        (new_id_shop, 'Void Phantom''s Glove', 'A glove used by the Void Phantom.', 150.00, 350, 0, false),
        (new_id_shop, 'Void Phantom''s Shoe', 'A shoe worn by the Void Phantom.', 150.00, 0, 40, false),
        (new_id_shop, 'Void Phantom''s Ring', 'A ring imbued with the power of the Void Phantom.', 250.00, 200, 20, false),
        (new_id_shop, 'Void Phantom''s Necklace', 'A necklace worn by the Void Phantom.', 250.00, 150, 25, false);


    --Dungeons
    INSERT INTO Dungeon (id_player, name, description, level, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user, 'The Dark Forest', 'A dense forest filled with dangerous creatures.', 1, 2, 50.00, false),
        (NEW.id_user, 'The Cursed Castle', 'An ancient castle haunted by vengeful spirits.', 2, 4, 100.00, false),
        (NEW.id_user, 'The Fiery Volcano', 'A volcano spewing molten lava and fiery monsters.', 3, 6, 150.00, false),
        (NEW.id_user, 'The Frozen Tundra', 'A frozen wasteland inhabited by ice creatures.', 4, 8, 200.00, false),
        (NEW.id_user, 'The Shadow Realm', 'A dark dimension filled with shadowy beings.', 5, 10, 250.00, false),
        (NEW.id_user, 'The Abyssal Depths', 'An underwater abyss teeming with aquatic horrors.', 6, 12, 300.00, false),
        (NEW.id_user, 'The Celestial Plane', 'A heavenly realm where divine beings reside.', 7, 14, 350.00, false),
        (NEW.id_user, 'The Infernal Abyss', 'A hellish realm of fire and brimstone.', 8, 16, 400.00, false),
        (NEW.id_user, 'The Astral Plane', 'A plane of existence beyond mortal comprehension.', 9, 18, 450.00, false),
        (NEW.id_user, 'The Void', 'A realm of nothingness where only darkness exists.', 10, 20, 500.00, false);


    --Enemies



    --TO DO : Add same initial configuration of the game for every player after creation
END;

DROP TRIGGER after_insert_player;

INSERT INTO User(username, password) VALUES ('admin', 'admin');
INSERT INTO Architect(id_user, level) VALUES (1, 999);

