package model.effects;

import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.DamagingAbility;
import model.world.Champion;

public class Disarm extends Effect {

	public Disarm(int d) {
		super("Disarm", d, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c) {
		c.getAbilities().add(new DamagingAbility("Punch", 0, 1, 1, AreaOfEffect.SINGLETARGET, 1, 50));
	}

	@Override
	public void remove(Champion c) {
		for (Ability a : c.getAbilities()) {
			if(a.getName().equals("Punch")) {
				c.getAbilities().remove(a);
				break;
			}
		}
	}

}
