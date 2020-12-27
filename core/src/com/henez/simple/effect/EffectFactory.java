package com.henez.simple.effect;

import com.henez.simple.Static;
import com.henez.simple.effect.impl.E_PopText;
import com.henez.simple.stats.damage.Damage;

public final class EffectFactory {
    private EffectFactory() {
    }

    private static void addEffect(Effect effect) {
        Static.effects.add(effect);
    }

    public static void createDamageText(Damage damage) {
        E_PopText effect = new E_PopText();
        effect.setGridPos(damage.getTarget().getGx(), damage.getTarget().getGy());
        effect.setText(damage.getDmgFinal() + "");
        addEffect(effect);
    }
}
