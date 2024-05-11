package org.example.services;

import org.example.models.*;
import org.example.repositories.*;

import java.util.ArrayList;

public class EnemyService {

    private EnemyRepository enemyRepository;
    private AssassinRepository assassinRepository;
    private MageRepository mageRepository;
    private TankRepository tankRepository;
    private BossAssassinRepository bossAssassinRepository;
    private BossMageRepository bossMageRepository;
    private BossTankRepository bossTankRepository;

    public EnemyService() {
        this.enemyRepository = new EnemyRepository();
        this.assassinRepository = new AssassinRepository();
        this.mageRepository = new MageRepository();
        this.tankRepository = new TankRepository();
        this.bossAssassinRepository = new BossAssassinRepository();
        this.bossMageRepository = new BossMageRepository();
        this.bossTankRepository = new BossTankRepository();
    }

    public ArrayList<Enemy> getEnemiesByEnemiesId(ArrayList<Integer> enemiesId) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        BossAssassin bossAssassin = null;
        BossMage bossMage = null;
        BossTank bossTank = null;

        for (int id : enemiesId) {
            if (bossAssassinRepository.get(id) != null)
            {
                bossAssassin = bossAssassinRepository.get(id);
                continue;
            }
            if (bossMageRepository.get(id) != null)
            {
                bossMage = bossMageRepository.get(id);
                continue;
            }
            if (bossTankRepository.get(id) != null)
            {
                bossTank = bossTankRepository.get(id);
                continue;
            }

            if (assassinRepository.get(id) != null)
            {
                Assassin assassin = assassinRepository.get(id);
                enemies.add(assassin);
                continue;
            }
            if (mageRepository.get(id) != null)
            {
                Mage mage = mageRepository.get(id);
                enemies.add(mage);
                continue;
            }
            if (tankRepository.get(id) != null)
            {
                Tank tank = tankRepository.get(id);
                enemies.add(tank);
                continue;
            }
        }
        if (bossAssassin != null)
        {
            enemies.add(bossAssassin);
        }
        if (bossMage != null)
        {
            enemies.add(bossMage);
        }
        if (bossTank != null)
        {
            enemies.add(bossTank);
        }

        return enemies;
    }

    public void updateEnemyEncountered(int id) {
        enemyRepository.updateEnemyEncountered(id);
    }
}
