package com.gamenc2014.Loan12SuQuan.model.enemy;

import java.util.ArrayList;

import com.gamenc2014.Loan12SuQuan.model.effect.Abstract_Effect;
import com.gamenc2014.Loan12SuQuan.model.effect.Hit_Effect;
import com.gamenc2014.Loan12SuQuan.model.gameObject.EnumDirectionList;
import com.gamenc2014.Loan12SuQuan.model.gameObject.GameObject;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;


public class Enemy extends GameObject implements Comparable<Enemy> {

	private EnumDirectionList direction;
	private int speed;
	private int damage;
	private int health;
	private float defense;
	private int evasionRate;
	private int gold;
	private int type;
	private int currentMileStone;
	private ArrayList<Abstract_Effect> effectList;
	private boolean isFlyUnit;
	private int roadID;
	private int distanceToDestination;
	private int dropRate;
	private int maxHp;

	public Enemy(int id, Coordinate position, EnemyDataUnit enemyData,
			EnumDirectionList direction, int roadID, int startMileStone,
			Scenario scenario) {
		super(id, enemyData.getDataID(), position);
		this.direction = direction;
		this.speed = enemyData.getSpeed();
		this.damage = enemyData.getDamage();
		this.health = enemyData.getHealth();
		this.defense = enemyData.getDefense();
		this.evasionRate = enemyData.getEvasionRate();
		this.gold = enemyData.getGold();
		this.type = enemyData.getType();
		this.effectList = new ArrayList<Abstract_Effect>();
		this.currentMileStone = startMileStone;
		this.isFlyUnit = enemyData.isFlyUnit();
		this.roadID = roadID;
		this.distanceToDestination = scenario.getDistance(this);
		this.maxHp = enemyData.getHealth();
		this.dropRate = enemyData.getGoldDropRate();
	}

	public String getName() {
		return EnumEnemyList.getEnemyData(dataID).getName();
	}

	public EnumDirectionList getDirection() {
		return direction;
	}

	public void setDirection(EnumDirectionList direction) {
		this.direction = direction;
	}

	public void setSpeed(int speed) {
		if (speed > -1) {
			this.speed = speed;
		} else {
			this.speed = 0;
		}
	}

	public int getSpeed() {
		return speed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setMaxHP(int hp) {
		this.maxHp = hp;
		this.health = hp;
	}

	private void setHealth(int health) {
		if (health > -1 && health <= getMaxHealth()) {
			this.health = health;
		} else if (health < 0) {
			this.health = 0;
		} else {
			this.health = getMaxHealth();
		}
	}

	public void addHealth(int hp) {
		if (hp > 0) {
			setHealth(this.health + hp);
		}
	}

	public void decreaseHealth(int hp) {
		if (hp > 0) {
			setHealth(this.health - hp);
		}

		this.addEffect(new Hit_Effect());
	}

	public int getMaxHealth() {
		return maxHp;
	}

	public int getHealthPercent() {
		return (this.health * 100) / getMaxHealth();
	}

	public float getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		if (defense > -1) {
			this.defense = defense;
		} else {
			this.defense = 0;
		}
	}

	public int getEvasionRate() {
		return evasionRate;
	}

	public void setEvasionRate(int evasionRate) {
		if (evasionRate > -1) {
			this.evasionRate = evasionRate;
		} else {
			this.evasionRate = 0;
		}
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isFlyUnit() {
		return isFlyUnit;
	}

	public void setFlyUnit(boolean isFlyUnit) {
		this.isFlyUnit = isFlyUnit;
	}

	public int getDropRate() {
		return this.dropRate;
	}

	public int getCurrentMileStone() {
		return currentMileStone;
	}

	public void setCurrentMileStone(int currentMileStone) {
		this.currentMileStone = currentMileStone;
	}

	public ArrayList<Abstract_Effect> getEffectList() {
		return effectList;
	}

	public void setEffectList(ArrayList<Abstract_Effect> effectList) {
		this.effectList = effectList;
	}

	public Abstract_Effect findEffect(int effectID) {
		for (Abstract_Effect effect : effectList) {
			if (effect.getEffectType() == effectID) {
				return effect;
			}
		}
		return null;
	}

	public void addEffect(Abstract_Effect effect) {
		effect.beforeAct(this);
		this.effectList.add(effect);
	}

	public int getRoadID() {
		return roadID;
	}

	public void setRoadID(int roadID) {
		this.roadID = roadID;
	}

	public int getStateID() {
		switch (direction) {
		case UP_DIRECTION:
			return (dataID / 10) * 3 + 2;
		case DOWN_DIRECTION:
			return (dataID / 10) * 3 + 1;
		case LEFT_DIRECTION:
			return (dataID / 10) * 3;
		case RIGHT_DIRECTION:
			return (dataID / 10) * 3;
		default:
			return (dataID / 10) * 3;
		}
	}

	public boolean isDropTreasure() {
		if (type == IEnemyConstants.DROP_TREASURE) {
			return true;
		} else {
			return false;
		}
	}

	public void update(float delta, Scenario scenario) {
		super.update(delta, scenario);
		Abstract_Effect temp;
		for (int i = effectList.size() - 1; i > -1; i--) {
			temp = effectList.get(i);
			if (temp.isEnd() == false) {
				temp.update(this, delta);
			} else {
				temp.afterAct(this);
				effectList.remove(i);
			}
		}

		switch (direction) {
		case UP_DIRECTION:
			this.position.addY((int) (-speed * delta));
			break;
		case DOWN_DIRECTION:
			this.position.addY((int) (speed * delta));
			break;
		case LEFT_DIRECTION:
			this.position.addX((int) (-speed * delta));
			break;
		case RIGHT_DIRECTION:
			this.position.addX((int) (speed * delta));
			break;
		}

		this.distanceToDestination = scenario.getDistance(this);
	}

	@Override
	public int compareTo(Enemy enemy) {
		if (this.getY() > enemy.getY()) {
			return 1;
		} else if (this.getY() < enemy.getY()) {
			return -1;
		} else {
			return 0;
		}
	}

	public int getDistanceToDestination() {
		return distanceToDestination;
	}

	public void setDistanceToDestination(int distanceToDestination) {
		this.distanceToDestination = distanceToDestination;
	}
}
