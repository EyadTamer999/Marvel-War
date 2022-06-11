package model.effects;

import model.world.Champion;

public abstract class Effect implements Cloneable{

	private String name;
	private int duration;
	private EffectType type;

	public Effect(String n, int d, EffectType t) {
		name = n;
		duration = d;
		type = t;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public EffectType getType() {
		return type;
	}
	
	public abstract void apply(Champion c);
	
	public abstract void remove(Champion c);
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
