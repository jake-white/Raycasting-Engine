package combat;

import java.util.Random;

public class CombatSpawns {
	public final int MAX_ENEMIES = 10;
	Enemy[] enemyList = new Enemy[MAX_ENEMIES];
	Random rand = new Random();

	public void initialSpawn() {
		for (int i = 0; i < MAX_ENEMIES; ++i) {
			EnemyType[] possible = EnemyType.values(); // getting all types
			EnemyType nextEnemyType = possible[rand.nextInt(possible.length)]; // randomly selecting a type
			Enemy nextEnemy = new Enemy(nextEnemyType); // creating an enemy
			enemyList[i] = nextEnemy; // adding enemy to the list
		}
	}

	public Enemy[] getEnemyList() {
		return enemyList;
	}

}
