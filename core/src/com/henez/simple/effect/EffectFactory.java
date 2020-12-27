package com.henez.simple.effect;

import com.henez.simple.Static;
import com.henez.simple.atlas.imgs.ImgIcon7;
import com.henez.simple.effect.impl.E_PopImg;
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

    public static void createCancel(int x, int y) {
        E_PopImg effect = new E_PopImg();
        effect.setGridPos(x, y);
        effect.setTex(ImgIcon7.cancel.asTex());
        addEffect(effect);
    }
}
