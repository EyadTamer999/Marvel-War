package model.effects;

import model.world.Champion;

public class Embrace extends Effect {

	public Embrace(int d) {
		super("Embrace", d, EffectType.BUFF);
	}

	@Override
	public void apply(Champion c) {
		c.setMana((int)(c.getMana() * 1.2));
		c.setCurrentHP(c.getCurrentHP() + (int)(c.getMaxHP() * 0.2));
		c.setSpeed((int) (c.getSpeed() * 1.2));
		c.setAttackDamage((int) (c.getAttackDamage() * 1.2));
	}

	@Override
	public void remove(Champion c) {
		c.setSpeed((int) (c.getSpeed() * 5 / 6.0));
		c.setAttackDamage((int) (c.getAttackDamage() * 5 / 6.0));
	}

}
