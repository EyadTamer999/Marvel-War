package model.world;

import java.awt.Point;

public interface Damageable {
	
	void setCurrentHP(int hp);
	int getCurrentHP();
	Point getLocation();

}
