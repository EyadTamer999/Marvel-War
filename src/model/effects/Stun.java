package model.effects;

import model.world.Champion;
import model.world.Condition;

public class Stun extends Effect {

	public Stun(int d) {
		super("Stun", d, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c) {
		c.setCondition(Condition.INACTIVE);
	}

	@Override
	public void remove(Champion c) {
		for (Effect e : c.getAppliedEffects()) {
			if(e instanceof Root) {
				c.setCondition(Condition.ROOTED);
				return;
			}
		}
		c.setCondition(Condition.ACTIVE);
	}

}
