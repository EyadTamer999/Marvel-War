package model.world;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;

public class Hero extends Champion {

	public Hero(String name, int maxHP, int mana, int maxActionsPerTurn, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, mana, maxActionsPerTurn, speed, attackRange, attackDamage);
	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion champion : targets) {
			ArrayList<Effect> temp = new ArrayList<Effect>();
			for (Effect e : champion.getAppliedEffects()) {
				if (e.getType() != EffectType.DEBUFF) {
					 temp.add(e);
				}
			}
			champion.getAppliedEffects().clear();
			for (Effect e : temp) {
				champion.getAppliedEffects().add(e);
			}
			Embrace e = new Embrace(2);
			e.apply(champion);
			champion.getAppliedEffects().add(e);
		}
	}

}
