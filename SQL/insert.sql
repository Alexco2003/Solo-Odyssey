
CREATE TRIGGER after_insert_player
AFTER INSERT
ON Player
FOR EACH ROW
BEGIN

    --Shop
    INSERT INTO Shop(id_shop,id_player, name) VALUES (NEW.id_user,NEW.id_user, 'The Architect''s Emporium');

    --Quests

    -- Quests requiring specific actions or tasks
    INSERT INTO Quest (id_quest, id_player, name, description, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user*21, NEW.id_user, 'The Lost Key', 'Find the hidden key to unlock the treasure chest.', 2, 150.50, false),
        (NEW.id_user*21+1, NEW.id_user, 'The Enigmatic Riddle', 'Solve the riddle to uncover the hidden passage.', 4, 60.60, false),
        (NEW.id_user*21+2, NEW.id_user, 'The Great Escape', 'Navigate through the labyrinth and reach the exit.', 3, 200.25, false),
        (NEW.id_user*21+3, NEW.id_user, 'The Button Press Challenge', 'Press the button space 100 times within a minute.', 5, 175.50, false),
        (NEW.id_user*21+4, NEW.id_user, 'The Timing Test', 'Press the button X when the clock strikes midnight.', 1, 80.00, false),
        (NEW.id_user*21+5, NEW.id_user, 'The Logic Puzzle', 'Solve the match calculus question to reveal the hidden code.', 7, 60.00, false),
        (NEW.id_user*21+6, NEW.id_user, 'The Maze Runner', 'Navigate the maze and find the hidden treasure.', 10, 110.40, false),
        (NEW.id_user*21+7, NEW.id_user, 'The Crystal Collection', 'Collect 10 crystals scattered across the land.', 8, 85.00, false),
        (NEW.id_user*21+8, NEW.id_user, 'The Time Traveler', 'Travel through time and return with evidence of your journey.', 9, 90.00, false),
        (NEW.id_user*21+9, NEW.id_user, 'The Ancient Relic', 'Retrieve the ancient relic guarded by mythical creatures.', 6, 100.00, false);

    -- Dungeon defeat quests
    INSERT INTO Quest (id_quest, id_player, name, description, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user*21+10, NEW.id_user, 'Defeated 1 Dungeon', 'Successfully defeat 1 dungeon.', 5, 200.00, false),
        (NEW.id_user*21+11, NEW.id_user, 'Defeated 2 Dungeons', 'Successfully defeat 2 dungeons.', 10, 400.00, false),
        (NEW.id_user*21+12, NEW.id_user, 'Defeated 3 Dungeons', 'Successfully defeat 3 dungeons.', 15, 600.00, false),
        (NEW.id_user*21+13, NEW.id_user, 'Defeated 5 Dungeons', 'Successfully defeat 5 dungeons.', 20, 800.00, false),
        (NEW.id_user*21+14, NEW.id_user, 'Defeated 10 Dungeons', 'Successfully defeat 10 dungeons.', 25, 1000.00, false);

    -- Secret endgame quest
    INSERT INTO Quest (id_quest, id_player, name, description, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user*21+15, NEW.id_user, 'The Final Confrontation', 'Details will be revealed soon...', 100, 10000.00, false);

    -- PVP quests
    INSERT INTO Quest (id_quest, id_player, name, description, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user*21+16, NEW.id_user, 'Defeated 1 Player', 'Successfully defeat 1 player in PVP.', 20, 250.00, false),
        (NEW.id_user*21+17, NEW.id_user, 'Defeated 2 Players', 'Successfully defeat 2 players in PVP.', 25, 500.00, false),
        (NEW.id_user*21+18, NEW.id_user, 'Defeated 3 Players', 'Successfully defeat 3 players in PVP.', 30, 750.00, false),
        (NEW.id_user*21+19, NEW.id_user, 'Defeated 4 Players', 'Successfully defeat 4 players in PVP.', 40, 1000.00, false),
        (NEW.id_user*21+20, NEW.id_user, 'Defeated 5 Players', 'Successfully defeat 5 players in PVP.', 50, 1250.00, false);

    --Items
    INSERT INTO Item (id_item, id_shop, name, description, price, damage, health, isBought)
    VALUES
        (NEW.id_user*97,NEW.id_user, 'Wooden Sword', 'A basic wooden sword.', 10.50, 10, 5, false),
        (NEW.id_user*97+1, NEW.id_user, 'Health Potion', 'Boosts health.', 20.25, 0, 15, false),
        (NEW.id_user*97+2, NEW.id_user, 'Iron Armor', 'Protects against damage.', 30.75, 0, 25, false),
        (NEW.id_user*97+3, NEW.id_user, 'Magic Wand', 'A wand that enhances magic abilities.', 40.80, 20, 5, false),
        (NEW.id_user*97+4, NEW.id_user, 'Speed Boots', 'Increases movement speed.', 50.60, 0, 5, false),
        (NEW.id_user*97+5, NEW.id_user, 'Fireball Scroll', 'Unleashes a powerful fireball.', 60.95, 30, 0, false),
        (NEW.id_user*97+6, NEW.id_user, 'Ice Shield', 'Creates a shield of ice.', 70.35, 0, 20, false),
        (NEW.id_user*97+7, NEW.id_user, 'Poison Dagger', 'Inflicts poison damage.', 80.20, 15, 5, false),
        (NEW.id_user*97+8, NEW.id_user, 'Thunder Staff', 'Summons lightning bolts.', 90.45, 25, 5, false),
        (NEW.id_user*97+9, NEW.id_user, 'Healing Staff', 'Restores health to allies.', 100.15, 0, 30, false),
        (NEW.id_user*97+10, NEW.id_user, 'Shadow Cloak', 'Grants invisibility.', 110.70, 0, 5, false),
        (NEW.id_user*97+11, NEW.id_user, 'Soul Gem', 'Absorbs enemy souls.', 120.90, 30, 5, false),
        (NEW.id_user*97+12, NEW.id_user, 'Dragon Scale Armor', 'Legendary armor.', 130.80, 0, 40, false),
        (NEW.id_user*97+13, NEW.id_user, 'Phoenix Feather', 'Blesses the player.', 140.45, 0, 10, false),
        (NEW.id_user*97+14, NEW.id_user, 'Elixir of Immortality', 'Grants eternal life.', 150.30, 0, 10, false),
        (NEW.id_user*97+15, NEW.id_user, 'Ancient Rune', 'Unleashes ancient powers.', 160.70, 40, 20, false),
        (NEW.id_user*97+16, NEW.id_user, 'Celestial Bow', 'Shoots arrows of light.', 170.60, 35, 5, false),
        (NEW.id_user*97+17, NEW.id_user, 'Vampire Fang', 'Drains enemy health.', 180.25, 20, 5, false),
        (NEW.id_user*97+18, NEW.id_user, 'Titan Gauntlet', 'Crushes enemies with immense strength.', 190.85, 50, 5, false),
        (NEW.id_user*97+19, NEW.id_user, 'Mystic Orb', 'Manipulates the elements.', 200.75, 45, 25, false),
        (NEW.id_user*97+20, NEW.id_user, 'Golden Apple', 'Grants permanent health boost.', 210.90, 0, 5, false),
        (NEW.id_user*97+21, NEW.id_user, 'Divine Sword', 'A sword blessed by the gods.', 220.55, 60, 5, false),
        (NEW.id_user*97+22, NEW.id_user, 'Crystal Staff', 'Channels crystal energy.', 230.35, 55, 5, false),
        (NEW.id_user*97+23, NEW.id_user, 'Shadow Amulet', 'Conceals the user in darkness.', 240.20, 0, 30, false),
        (NEW.id_user*97+24, NEW.id_user, 'Soul Reaper Scythe', 'Harvests enemy souls.', 250.45, 65, 5, false),
        (NEW.id_user*97+25, NEW.id_user, 'Dragonfire Shield', 'Blocks dragonfire attacks.', 260.95, 0, 35, false),
        (NEW.id_user*97+26, NEW.id_user, 'Phoenix Tear', 'Revives the soul of the Phoenix.', 270.30, 0, 15, false),
        (NEW.id_user*97+27, NEW.id_user, 'Elixir of Ascension', 'Ascends the user to a higher plane.', 280.80, 0, 15, false),
        (NEW.id_user*97+28, NEW.id_user, 'Ancient Scroll', 'Unlocks ancient knowledge.', 290.75, 70, 10, false),
        (NEW.id_user*97+29, NEW.id_user, 'Celestial Staff', 'Channels celestial energy.', 300.60, 75, 5, false),
        (NEW.id_user*97+30, NEW.id_user, 'Vampire Cloak', 'Drains enemy health over time.', 310.25, 25, 5, false),
        (NEW.id_user*97+31, NEW.id_user, 'Titan Hammer', 'Crushes enemies with divine power.', 320.85, 80, 5, false),
        (NEW.id_user*97+32, NEW.id_user, 'Mystic Crystal', 'Harnesses the power of crystals.', 330.70, 85, 5, false),
        (NEW.id_user*97+33, NEW.id_user, 'Golden Shield', 'Grants permanent armor boost.', 340.90, 0, 500, false),
        (NEW.id_user*97+34, NEW.id_user, 'Divine Bow', 'Shoots arrows of divine light.', 350.55, 80, 5, false),
        (NEW.id_user*97+35, NEW.id_user, 'Crystal Orb', 'Manipulates crystal elements.', 360.35, 90, 5, false),
        (NEW.id_user*97+36, NEW.id_user, 'Shadow Cloak', 'Conceals the user in shadow.', 370.20, 0, 5, false),

        --Boss items

        --Assassin
        (NEW.id_user*97+37, NEW.id_user, 'Vorpal Shadowblade''s Helmet', 'A helm worn by the Vorpal Shadowblade.', 200.00, 150, 0, false),
        (NEW.id_user*97+38, NEW.id_user, 'Vorpal Shadowblade''s Armor', 'Protective armor worn by the Vorpal Shadowblade.', 300.00, 50, 100, false),
        (NEW.id_user*97+39, NEW.id_user, 'Vorpal Shadowblade''s Glove', 'A glove used by the Vorpal Shadowblade.', 150.00, 350, 0, false),
        (NEW.id_user*97+40, NEW.id_user, 'Vorpal Shadowblade''s Shoe', 'A shoe worn by the Vorpal Shadowblade.', 150.00, 0, 40, false),
        (NEW.id_user*97+41, NEW.id_user, 'Vorpal Shadowblade''s Ring', 'A ring imbued with the power of the Vorpal Shadowblade.', 250.00, 200, 20, false),
        (NEW.id_user*97+42, NEW.id_user, 'Vorpal Shadowblade''s Necklace', 'A necklace worn by the Vorpal Shadowblade.', 250.00, 150, 25, false),

        --Tank
        (NEW.id_user*97+43, NEW.id_user, 'Colossus Steelhide''s Helmet', 'A massive helmet forged from steel.', 150.00, 0, 100, false),
        (NEW.id_user*97+44, NEW.id_user, 'Colossus Steelhide''s Armor', 'Thick armor made from the toughest steel.', 250.00, 0, 200, false),
        (NEW.id_user*97+45, NEW.id_user, 'Colossus Steelhide''s Gauntlets', 'Heavy gauntlets that pack a punch.', 200.00, 50, 100, false),
        (NEW.id_user*97+46, NEW.id_user, 'Colossus Steelhide''s Boots', 'Boots designed for stomping enemies.', 200.00, 100, 50, false),
        (NEW.id_user*97+47, NEW.id_user, 'Colossus Steelhide''s Ring', 'A ring imbued with protective magic.', 300.00, 0, 300, false),
        (NEW.id_user*97+48, NEW.id_user, 'Colossus Steelhide''s Necklace', 'A necklace that enhances endurance.', 300.00, 0, 300, false),

        --Mage
        (NEW.id_user*97+49, NEW.id_user, 'Ignis Pyreborn''s Helm', 'A helm infused with the essence of fire.', 200.00, 80, 80, false),
        (NEW.id_user*97+50, NEW.id_user, 'Ignis Pyreborn''s Robe', 'A robe woven from flames.', 300.00, 40, 120, false),
        (NEW.id_user*97+51, NEW.id_user, 'Ignis Pyreborn''s Bracers', 'Bracers that channel fiery magic.', 150.00, 60, 60, false),
        (NEW.id_user*97+52, NEW.id_user, 'Ignis Pyreborn''s Boots', 'Boots that leave behind trails of fire.', 150.00, 20, 100, false),
        (NEW.id_user*97+53, NEW.id_user, 'Ignis Pyreborn''s Ring', 'A ring forged in the heart of a volcano.', 250.00, 100, 50, false),
        (NEW.id_user*97+54, NEW.id_user, 'Ignis Pyreborn''s Amulet', 'An amulet pulsating with fiery energy.', 250.00, 80, 80, false),

        --Assassin
        (NEW.id_user*97+55, NEW.id_user, 'Frostbite''s Frostfang', 'A dagger forged from enchanted ice.', 200.00, 180, 0, false),
        (NEW.id_user*97+56, NEW.id_user, 'Frostbite''s Frostcloak', 'A cloak that freezes the air around it.', 300.00, 0, 250, false),
        (NEW.id_user*97+57, NEW.id_user, 'Frostbite''s Frostblade', 'A blade as cold as the heart of winter.', 150.00, 250, 0, false),
        (NEW.id_user*97+58, NEW.id_user, 'Frostbite''s Frostboots', 'Boots that leave frozen footprints in their wake.', 150.00, 0, 180, false),
        (NEW.id_user*97+59, NEW.id_user, 'Frostbite''s Frostband', 'A band that channels the chill of the tundra.', 250.00, 220, 0, false),
        (NEW.id_user*97+60, NEW.id_user, 'Frostbite''s Frostpendant', 'A pendant that emanates an icy aura.', 250.00, 0, 200, false),

        --Mage
        (NEW.id_user*97+61, NEW.id_user, 'Eldritch Spellbinder''s Helm', 'A mystical helmet imbued with eldritch power.', 150.00, 10, 20, false),
        (NEW.id_user*97+62, NEW.id_user, 'Eldritch Spellbinder''s Armor', 'Enchanted armor that harnesses eldritch energy.', 250.00, 15, 25, false),
        (NEW.id_user*97+63, NEW.id_user, 'Eldritch Spellbinder''s Gauntlets', 'Magical gauntlets that enhance spellcasting.', 200.00, 20, 15, false),
        (NEW.id_user*97+64, NEW.id_user, 'Eldritch Spellbinder''s Boots', 'Boots infused with otherworldly agility.', 180.00, 15, 20, false),
        (NEW.id_user*97+65, NEW.id_user, 'Eldritch Spellbinder''s Ring', 'A ring pulsating with eldritch power.', 300.00, 10, 30, false),
        (NEW.id_user*97+66, NEW.id_user, 'Eldritch Spellbinder''s Necklace', 'A mystical necklace that boosts magical prowess.', 350.00, 20, 15, false),

         --Tank
        (NEW.id_user*97+67, NEW.id_user, 'Leviathan''s Helm', 'A helm crafted from Leviathan scales.', 150.00, 0, 200, false),
        (NEW.id_user*97+68, NEW.id_user, 'Leviathan''s Shell', 'A shell capable of withstanding immense pressure.', 250.00, 0, 300, false),
        (NEW.id_user*97+69, NEW.id_user, 'Leviathan''s Gauntlets', 'Gauntlets forged from hardened coral.', 200.00, 0, 250, false),
        (NEW.id_user*97+70, NEW.id_user, 'Leviathan''s Greaves', 'Greaves reinforced with underwater alloys.', 200.00, 0, 200, false),
        (NEW.id_user*97+71, NEW.id_user, 'Leviathan''s Ring', 'A ring infused with the essence of the ocean depths.', 300.00, 0, 350, false),
        (NEW.id_user*97+72, NEW.id_user, 'Leviathan''s Amulet', 'An amulet that grants dominion over the seas.', 300.00, 0, 300, false),

        --Mage
        (NEW.id_user*97+73, NEW.id_user, 'Celestial Archon''s Circlet', 'A circlet adorned with celestial gemstones.', 200.00, 80, 80, false),
        (NEW.id_user*97+74, NEW.id_user, 'Celestial Archon''s Robes', 'Robes infused with celestial energy.', 300.00, 50, 100, false),
        (NEW.id_user*97+75, NEW.id_user, 'Celestial Archon''s Gloves', 'Gloves that channel divine magic.', 150.00, 100, 50, false),
        (NEW.id_user*97+76, NEW.id_user, 'Celestial Archon''s Sandals', 'Sandals that grant ethereal mobility.', 150.00, 0, 100, false),
        (NEW.id_user*97+77, NEW.id_user, 'Celestial Archon''s Ring', 'A ring blessed by celestial beings.', 250.00, 50, 100, false),
        (NEW.id_user*97+78, NEW.id_user, 'Celestial Archon''s Pendant', 'A pendant imbued with celestial power.', 250.00, 100, 50, false),

        --Assassin
        (NEW.id_user*97+79, NEW.id_user, 'Infernal Revenant''s Mask', 'A mask veiled in flames.', 200.00, 150, 0, false),
        (NEW.id_user*97+80, NEW.id_user, 'Infernal Revenant''s Cloak', 'A cloak engulfed in infernal fire.', 300.00, 0, 200, false),
        (NEW.id_user*97+81, NEW.id_user, 'Infernal Revenant''s Blades', 'Blades forged from hellfire.', 150.00, 200, 0, false),
        (NEW.id_user*97+82, NEW.id_user, 'Infernal Revenant''s Boots', 'Boots that leave trails of fire.', 150.00, 0, 150, false),
        (NEW.id_user*97+83, NEW.id_user, 'Infernal Revenant''s Ring', 'A ring infused with infernal power.', 250.00, 200, 0, false),
        (NEW.id_user*97+84, NEW.id_user, 'Infernal Revenant''s Amulet', 'An amulet that harnesses hellfire.', 250.00, 0, 200, false),

        --Tank
        (NEW.id_user*97+85, NEW.id_user, 'Astral Overlord''s Helmet', 'A helm worn by the Astral Overlord.', 200.00, 150, 0, false),
        (NEW.id_user*97+86, NEW.id_user, 'Astral Overlord''s Armor', 'Protective armor worn by the Astral Overlord.', 300.00, 50, 100, false),
        (NEW.id_user*97+87, NEW.id_user, 'Astral Overlord''s Glove', 'A glove used by the Astral Overlord.', 150.00, 350, 0, false),
        (NEW.id_user*97+88, NEW.id_user, 'Astral Overlord''s Shoe', 'A shoe worn by the Astral Overlord.', 150.00, 0, 40, false),
        (NEW.id_user*97+89, NEW.id_user, 'Astral Overlord''s Ring', 'A ring imbued with the power of the Astral Overlord.', 250.00, 200, 20, false),
        (NEW.id_user*97+90, NEW.id_user, 'Astral Overlord''s Necklace', 'A necklace worn by the Astral Overlord.', 250.00, 150, 25, false),

        --Mage
        (NEW.id_user*97+91, NEW.id_user, 'Void Phantom''s Helmet', 'A helm worn by the Void Phantom.', 200.00, 150, 0, false),
        (NEW.id_user*97+92, NEW.id_user, 'Void Phantom''s Armor', 'Protective armor worn by the Void Phantom.', 300.00, 50, 100, false),
        (NEW.id_user*97+93, NEW.id_user, 'Void Phantom''s Glove', 'A glove used by the Void Phantom.', 150.00, 350, 0, false),
        (NEW.id_user*97+94, NEW.id_user, 'Void Phantom''s Shoe', 'A shoe worn by the Void Phantom.', 150.00, 0, 40, false),
        (NEW.id_user*97+95, NEW.id_user, 'Void Phantom''s Ring', 'A ring imbued with the power of the Void Phantom.', 250.00, 200, 20, false),
        (NEW.id_user*97+96, NEW.id_user, 'Void Phantom''s Necklace', 'A necklace worn by the Void Phantom.', 250.00, 150, 25, false);


--    --Player Inventory - TODO: Add starting items
--    INSERT INTO PlayerInventory (id_player, id_item, quantity)

    --Dungeons
    INSERT INTO Dungeon (id_dungeon, id_player, name, description, level, rewardLevel, rewardMoney, completed)
    VALUES
        (NEW.id_user*10,NEW.id_user, 'The Dark Forest', 'A dense forest filled with dangerous creatures.', 1, 2, 50.00, false),
        (NEW.id_user*10+1,NEW.id_user, 'The Cursed Castle', 'An ancient castle haunted by vengeful spirits.', 2, 4, 100.00, false),
        (NEW.id_user*10+2,NEW.id_user, 'The Fiery Volcano', 'A volcano spewing molten lava and fiery monsters.', 3, 6, 150.00, false),
        (NEW.id_user*10+3,NEW.id_user, 'The Frozen Tundra', 'A frozen wasteland inhabited by ice creatures.', 4, 8, 200.00, false),
        (NEW.id_user*10+4,NEW.id_user, 'The Shadow Realm', 'A dark dimension filled with shadowy beings.', 5, 10, 250.00, false),
        (NEW.id_user*10+5,NEW.id_user, 'The Abyssal Depths', 'An underwater abyss teeming with aquatic horrors.', 6, 12, 300.00, false),
        (NEW.id_user*10+6,NEW.id_user, 'The Celestial Plane', 'A heavenly realm where divine beings reside.', 7, 14, 350.00, false),
        (NEW.id_user*10+7,NEW.id_user, 'The Infernal Abyss', 'A hellish realm of fire and brimstone.', 8, 16, 400.00, false),
        (NEW.id_user*10+8,NEW.id_user, 'The Astral Plane', 'A plane of existence beyond mortal comprehension.', 9, 18, 450.00, false),
        (NEW.id_user*10+9,NEW.id_user, 'The Void', 'A realm of nothingness where only darkness exists.', 10, 20, 500.00, false);

    --Enemies
    INSERT INTO Enemy (id_enemy, name, description, health, damage, encountered)
    VALUES
        --Assassins
        (NEW.id_user*25, 'Shadow Panther', 'A stealthy panther that strikes from the shadows.', 80, 140, false),
        (NEW.id_user*25+1, 'Night Serpent', 'A deadly serpent that hunts under the cover of darkness.', 80, 140, false),
        (NEW.id_user*25+2, 'Ghost Wolf', 'A spectral wolf that moves like a phantom.', 80, 140, false),
        (NEW.id_user*25+3, 'Silent Owl', 'An owl known for its silent but deadly swoop.', 80, 140, false),
        (NEW.id_user*25+4, 'Crimson Fox', 'A cunning fox with a deadly bite.', 80, 140, false),
        --Boss Assassins
        --Dungeon 1
        (NEW.id_user*25+5, 'Vorpal Shadowblade', 'A formidable assassin, master of The Dark Forest, that deadly strikes.', 100, 200, false),
        --Dungeon 4
        (NEW.id_user*25+6, 'Frostbite', 'The ruler of The Frozen Tundra is a deadly assassin wielding a dagger forged from enchanted ice.', 200, 180, false),
        --Dungeon 8
        (NEW.id_user*25+7, 'Infernal Revenant', 'The ruler of The Infernal Abyss is a fearsome assassin wreathed in infernal flames.', 200, 200, false),

        --Tanks
        (NEW.id_user*25+8, 'Iron Tortoise', 'A tortoise with an impenetrable iron shell.', 250, 30, false),
        (NEW.id_user*25+9, 'Stone Golem', 'A golem made of solid stone, impervious to most attacks.', 250, 30, false),
        (NEW.id_user*25+10, 'Steel Rhino', 'A rhino known for its steel-like hide.', 250, 30, false),
        (NEW.id_user*25+11, 'Granite Elephant', 'An elephant whose skin is as hard as granite.', 250, 30, false),
        (NEW.id_user*25+12, 'Diamond Armadillo', 'An armadillo with a diamond-hard shell.', 250, 30, false),
        --Boss Tanks
        --Dungeon 2
        (NEW.id_user*25+13, 'Colossus Steelhide', 'The guardian of The Cursed Castle, Colossus Steelhide is a massive creature with armor as tough as the castle walls.', 300, 50, false),
        --Dungeon 6
        (NEW.id_user*25+14, 'Leviathan', 'The ruler of The Abyssal Depths, Leviathan is a massive sea creature with impenetrable scales.', 300, 50, false),
        --Dungeon 9
        (NEW.id_user*25+15, 'Astral Overlord', 'The ruler of The Astral Plane, Astral Overlord is a divine being with impenetrable armor made of celestial energy .', 300, 50, false),

        --Mages
        (NEW.id_user*25+16, 'Fire Sprite', 'A sprite that manipulates fire to attack its enemies.', 125, 125, false),
        (NEW.id_user*25+17, 'Wind Sylph', 'A sylph that controls the wind to buffet its foes.', 125, 125, false),
        (NEW.id_user*25+18, 'Water Nymph', 'A nymph that uses water magic to attack.', 125, 125, false),
        (NEW.id_user*25+19, 'Earth Pixie', 'A pixie that uses earth magic to defend itself.', 125, 125, false),
        (NEW.id_user*25+20, 'Lightning Djinn', 'A djinn that uses lightning magic to shock its enemies.', 125, 125, false),
        --Boss Mages
        --Dungeon 3
        (NEW.id_user*25+21, 'Ignis Pyreborn', 'The ruler of The Fiery Volcano, Ignis Pyreborn commands the power of fire.', 200, 80, false),
        --Dungeon 5
        (NEW.id_user*25+22, 'Eldritch Spellbinder', 'The ruler of The Shadow Realm, Eldritch Spellbinder commands the dark forces within this realm.', 200, 80, false),
        --Dungeon 7
        (NEW.id_user*25+23, 'Celestial Archon', 'The ruler of The Celestial Plane, Celestial Archon is a divine being with immense power.', 200, 80, false),
        --Dungeon 10
        (NEW.id_user*25+24, 'Void Phantom', 'The ruler of The Void, Void Phantom is a being of pure darkness.', 200, 80, false);

    --Enemies Assasin
    INSERT INTO Assassin (id_enemy, criticalChance)
    VALUES
        (NEW.id_user*25, 30),
        (NEW.id_user*25+1, 30),
        (NEW.id_user*25+2, 30),
        (NEW.id_user*25+3, 30),
        (NEW.id_user*25+4, 30),

        --Boss Assassins
        (NEW.id_user*25+5, 50),
        (NEW.id_user*25+6, 50),
        (NEW.id_user*25+7, 50);

    --Enemies Tank
    INSERT INTO Tank (id_enemy, armor)
    VALUES
        (NEW.id_user*25+8, 100),
        (NEW.id_user*25+9, 100),
        (NEW.id_user*25+10, 100),
        (NEW.id_user*25+11, 100),
        (NEW.id_user*25+12, 100),

        --Boss Tanks
        (NEW.id_user*25+13, 150),
        (NEW.id_user*25+14, 150),
        (NEW.id_user*25+15, 150);

    --Enemies Mage
    INSERT INTO Mage (id_enemy, mana)
    VALUES
        (NEW.id_user*25+16, 200),
        (NEW.id_user*25+17, 200),
        (NEW.id_user*25+18, 200),
        (NEW.id_user*25+19, 200),
        (NEW.id_user*25+20, 200),

        --Boss Mages
        (NEW.id_user*25+21, 250),
        (NEW.id_user*25+22, 250),
        (NEW.id_user*25+23, 250),
        (NEW.id_user*25+24, 250);

     --Enemies Assassin Boss
    INSERT INTO BossAssassin (id_enemy)
    VALUES
        (NEW.id_user*25+5),
        (NEW.id_user*25+6),
        (NEW.id_user*25+7);

    --Enemies Tank Boss
    INSERT INTO BossTank (id_enemy)
    VALUES
        (NEW.id_user*25+13),
        (NEW.id_user*25+14),
        (NEW.id_user*25+15);

    --Enemies Mage Boss
    INSERT INTO BossMage (id_enemy)
    VALUES
        (NEW.id_user*25+21),
        (NEW.id_user*25+22),
        (NEW.id_user*25+23),
        (NEW.id_user*25+24);


    --Boss Assassin Inventory
    INSERT INTO BossAssassinInventory (id_item, id_enemy)
    VALUES
        --Boss Assassin 1
        (NEW.id_user*97+37, NEW.id_user*25+5),
        (NEW.id_user*97+38, NEW.id_user*25+5),
        (NEW.id_user*97+39, NEW.id_user*25+5),
        (NEW.id_user*97+40, NEW.id_user*25+5),
        (NEW.id_user*97+41, NEW.id_user*25+5),
        (NEW.id_user*97+42, NEW.id_user*25+5),

        --Boss Assassin 2
        (NEW.id_user*97+55, NEW.id_user*25+6),
        (NEW.id_user*97+56, NEW.id_user*25+6),
        (NEW.id_user*97+57, NEW.id_user*25+6),
        (NEW.id_user*97+58, NEW.id_user*25+6),
        (NEW.id_user*97+59, NEW.id_user*25+6),
        (NEW.id_user*97+60, NEW.id_user*25+6),

        --Boss Assassin 3
        (NEW.id_user*97+79, NEW.id_user*25+7),
        (NEW.id_user*97+80, NEW.id_user*25+7),
        (NEW.id_user*97+81, NEW.id_user*25+7),
        (NEW.id_user*97+82, NEW.id_user*25+7),
        (NEW.id_user*97+83, NEW.id_user*25+7),
        (NEW.id_user*97+84, NEW.id_user*25+7);

    --Boss Tank Inventory
    INSERT INTO BossTankInventory (id_item, id_enemy)
    VALUES
        --Boss Tank 1
        (NEW.id_user*97+43, NEW.id_user*25+13),
        (NEW.id_user*97+44, NEW.id_user*25+13),
        (NEW.id_user*97+45, NEW.id_user*25+13),
        (NEW.id_user*97+46, NEW.id_user*25+13),
        (NEW.id_user*97+47, NEW.id_user*25+13),
        (NEW.id_user*97+48, NEW.id_user*25+13),

        --Boss Tank 2
        (NEW.id_user*97+67, NEW.id_user*25+14),
        (NEW.id_user*97+68, NEW.id_user*25+14),
        (NEW.id_user*97+69, NEW.id_user*25+14),
        (NEW.id_user*97+70, NEW.id_user*25+14),
        (NEW.id_user*97+71, NEW.id_user*25+14),
        (NEW.id_user*97+72, NEW.id_user*25+14),

        --Boss Tank 3
        (NEW.id_user*97+85, NEW.id_user*25+15),
        (NEW.id_user*97+86, NEW.id_user*25+15),
        (NEW.id_user*97+87, NEW.id_user*25+15),
        (NEW.id_user*97+88, NEW.id_user*25+15),
        (NEW.id_user*97+89, NEW.id_user*25+15),
        (NEW.id_user*97+90, NEW.id_user*25+15);

    --Boss Mage Inventory
    INSERT INTO BossMageInventory (id_item, id_enemy)
    VALUES
        --Boss Mage 1
        (NEW.id_user*97+49, NEW.id_user*25+21),
        (NEW.id_user*97+50, NEW.id_user*25+21),
        (NEW.id_user*97+51, NEW.id_user*25+21),
        (NEW.id_user*97+52, NEW.id_user*25+21),
        (NEW.id_user*97+53, NEW.id_user*25+21),
        (NEW.id_user*97+54, NEW.id_user*25+21),

        --Boss Mage 2
        (NEW.id_user*97+61, NEW.id_user*25+22),
        (NEW.id_user*97+62, NEW.id_user*25+22),
        (NEW.id_user*97+63, NEW.id_user*25+22),
        (NEW.id_user*97+64, NEW.id_user*25+22),
        (NEW.id_user*97+65, NEW.id_user*25+22),
        (NEW.id_user*97+66, NEW.id_user*25+22),

        --Boss Mage 3
        (NEW.id_user*97+73, NEW.id_user*25+23),
        (NEW.id_user*97+74, NEW.id_user*25+23),
        (NEW.id_user*97+75, NEW.id_user*25+23),
        (NEW.id_user*97+76, NEW.id_user*25+23),
        (NEW.id_user*97+77, NEW.id_user*25+23),
        (NEW.id_user*97+78, NEW.id_user*25+23),

        --Boss Mage 4
        (NEW.id_user*97+91, NEW.id_user*25+24),
        (NEW.id_user*97+92, NEW.id_user*25+24),
        (NEW.id_user*97+93, NEW.id_user*25+24),
        (NEW.id_user*97+94, NEW.id_user*25+24),
        (NEW.id_user*97+95, NEW.id_user*25+24),
        (NEW.id_user*97+96, NEW.id_user*25+24);


--TODO: Add dungeon enemies
--TODO: BALANCE VALUES

--    --Boss Assassin Inventory
--    INSERT INTO BossAssassinInventory (id_item, id_enemy)
--    VALUES
--        --Boss Assassin 1
--        (NEW.id_user*97+37, NEW.id_user*25+5),
--        (NEW.id_user*97+38, NEW.id_user*25+5),
--        (NEW.id_user*97+39, NEW.id_user*25+5),
--        (NEW.id_user*97+40, NEW.id_user*25+5),
--




--    --Dungeon 1
--
--    -- Tank enemy
--    INSERT INTO Enemy (id_dungeon, name, description, health, damage)
--    VALUES
--        (NEW.id_user*10, 'Forest Guardian', 'A massive creature with thick bark-like skin, guarding the heart of the forest.', 200, 50);
--
--    INSERT INTO Tank (id_enemy, armor)
--    VALUES
--        (LAST_INSERT_ID(), 100);
--
--    -- Mage enemy
--    INSERT INTO Enemy (id_dungeon, name, description, health, damage)
--    VALUES
--        (NEW.id_user*10, 'Wispweaver', 'An ethereal being that weaves spells from the very essence of the forest.', 150, 80);
--
--    INSERT INTO Mage (id_enemy, mana)
--    VALUES
--        (LAST_INSERT_ID(), 200);
--
--    -- Assassin enemy
--    INSERT INTO Enemy (id_dungeon, name, description, health, damage)
--    VALUES
--        (NEW.id_user*10, 'Shadowstalker', 'A stealthy predator lurking in the shadows, waiting to strike.', 100, 120);
--
--    INSERT INTO Assassin (id_enemy, criticalChance)
--    VALUES
--        (LAST_INSERT_ID(), 30);
--
--    -- Assassin Boss enemy
--    INSERT INTO Enemy (id_dungeon, name, description, health, damage)
--    VALUES
--        (NEW.id_user*10, 'Vorpal Shadowblade', 'A formidable assassin, master of The Dark Forest, that deadly strikes.', 100, 120);
--
--    INSERT INTO Assassin (id_enemy, criticalChance)
--    VALUES
--        (LAST_INSERT_ID(), 50);
--
--    INSERT INTO BossAssassin (id_enemy)
--    VALUES
--        (LAST_INSERT_ID());
--
--    INSERT INTO BossAssassinInventory (id_item, id_enemy)
--    VALUES
--        (NEW.id_user*10+1, LAST_INSERT_ID()),
--        (NEW.id_user*10+2, LAST_INSERT_ID()),
--        (NEW.id_user*10+3, LAST_INSERT_ID()),
--        (NEW.id_user*10+4, LAST_INSERT_ID()),
--        (NEW.id_user*10+5, LAST_INSERT_ID()),
--        (NEW.id_user*10+6, LAST_INSERT_ID());
--        -- Find items id for the boss


--    INSERT INTO Enemy (id_dungeon, name, description, health, damage)
--    values
--        (1, 'Goblin', 'A small, green-skinned creature with a penchant for mischief.', 10, 5),
--        (1, 'Wolf', 'A fierce predator with sharp teeth and claws.', 15, 7),
--        (1, 'Ent', 'A massive tree-like creature that guards the forest.', 20, 10),
--        (1, 'Witch', 'A spellcaster who uses dark magic to ensnare her foes.', 25, 12),
--        (1, 'Troll', 'A hulking brute with thick skin and a bad temper.', 30, 15),
--        (1, 'Orc', 'A fierce warrior with a lust for battle.', 35, 17),
--        (1, 'Harpy', 'A winged creature that attacks from the skies.', 40, 20),
--        (1, 'Basilisk', 'A serpent-like monster with a petrifying gaze.', 45, 22)



    --TODO : Add same initial configuration of the game for every player after creation
END;

DROP TRIGGER after_insert_player;

INSERT INTO User(username, password) VALUES ('admin', 'admin');
INSERT INTO Architect(id_user, level) VALUES (1, 999);

