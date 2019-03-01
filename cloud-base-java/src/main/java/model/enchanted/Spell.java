package model.enchanted;

import java.util.Random;

/**
 * 咒语类
 * @author shadow
 *
 */
public class Spell {
	private String spell;
	
	public Spell() {
		// TODO Auto-generated constructor stub
		spell = "spell" + (char)new Random().nextInt(10);
	}
	
	public String getSpell(){
		return spell;
	}
}
