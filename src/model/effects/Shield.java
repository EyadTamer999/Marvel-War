package model.effects;

import model.world.Champion;

public class Shield extends Effect {

	public Shield(int d) {
		super("Shield", d, EffectType.BUFF);
	}

	@Override
	public void apply(Champion c) {
		c.setSpeed((int) (c.getSpeed() * 1.02));
	}

	@Override
	public void remove(Champion c) {

		c.setSpeed((int) (c.getSpeed() / 1.02));
	}

}