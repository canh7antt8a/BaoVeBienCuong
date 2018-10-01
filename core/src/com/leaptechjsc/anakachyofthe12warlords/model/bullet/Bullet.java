package com.leaptechjsc.anakachyofthe12warlords.model.bullet;


import com.badlogic.gdx.math.Vector2;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.GameObject;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;
import com.leaptechjsc.anakachyofthe12warlords.model.enemy.Enemy;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.GameObject;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.Tower;

public class Bullet extends GameObject {

	private Vector2 velocity;
	private Coordinate destination;
	private boolean isEnd;
	private Tower tower;
	private Enemy target;
	private float flyTime;
	private float rotation;

	public Bullet(int id, int dataID, Coordinate position,
			Coordinate destination, Vector2 velocity, Tower tower,
			Enemy target, float flyTime) {
		super(id, dataID, position);
		this.destination = destination;
		this.velocity = velocity;
		this.isEnd = false;
		this.tower = tower;
		this.target = target;
		this.flyTime = flyTime;
		setRotation();
	}

	@Override
	public void update(float delta, Scenario scenario) {
		super.update(delta, scenario);
		this.position.addX((int) (velocity.x * delta));
		this.position.addY((int) (velocity.y * delta));
		if (stateTime > flyTime) {
			this.isEnd = true;
			if (target != null) {
				tower.damage(target, scenario);
			}
		}
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Coordinate getDestination() {
		return destination;
	}

	public void setDestination(Coordinate destination) {
		this.destination = destination;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public Enemy getTarget() {
		return target;
	}

	public void setTarget(Enemy target) {
		this.target = target;
	}

	public float getFlyTime() {
		return flyTime;
	}

	public void setFlyTime(float flyTime) {
		this.flyTime = flyTime;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setRotation() {
		float d = destination.getX() - this.position.getX();
		float k = destination.getY() - this.position.getY();
		if (k != 0) {
			if (k < 0) {
				this.rotation = (float) Math.toDegrees(Math.atan(d / k));
			} else {
				this.rotation = (float) Math.toDegrees(Math.PI
						+ Math.atan(d / k));
			}
		} else {
			if (d > 0) {
				this.rotation = -90;
			} else {
				this.rotation = 90;
			}
		}
	}
}
