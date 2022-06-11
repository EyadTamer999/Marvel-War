package model.effects;

import model.world.Champion;

public class Dodge extends Effect {

	public Dodge(int d) {
		super("Dodge", d, EffectType.BUFF);
	}

	@Override
	public void apply(Champion c) {
		c.setSpeed((int) (c.getSpeed() * 1.05));
	}

	@Override
	public void remove(Champion c) {
		c.setSpeed((int) (c.getSpeed() * 20/21.0));
		
	}

}
