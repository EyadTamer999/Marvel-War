package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int manaCost, int baseCooldown, int castRange, AreaOfEffect castArea,
			int actionsRequired, int damageAmount) {
		super(name, manaCost, baseCooldown, castRange, castArea, actionsRequired);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets) {
			d.setCurrentHP(d.getCurrentHP() - this.damageAmount);
		}
	}

}
