package model.effects;

import model.world.Champion;
import model.world.Condition;

public class Root extends Effect {

	public Root(int d) {
		super("Root", d, EffectType.DEBUFF);
	}

	@Override
	public void apply(Champion c) {
		if(c.getCondition() == Condition.ACTIVE)
			c.setCondition(Condition.ROOTED);
	}

	@Override
	public void remove(Champion c) {
		if(c.getCondition() == Condition.INACTIVE)
			return;
		for (Effect e : c.getAppliedEffects()) {
			if(e instanceof Root) {
				c.setCondition(Condition.ROOTED);
				return;
			}
		}
		c.setCondition(Condition.ACTIVE);
	}

}
