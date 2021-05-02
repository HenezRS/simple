package com.henez.simple.effect;

import com.badlogic.gdx.graphics.Color;
import com.henez.simple.Static;
import com.henez.simple.atlas.imgs.ImgIcon7;
import com.henez.simple.effect.impl.E_PopImg;
import com.henez.simple.effect.impl.E_PopText;
import com.henez.simple.enums.Colors;
import com.henez.simple.stats.damage.Damage;
import com.henez.simple.world.mapobjects.MapObject;

public final class EffectFactory {
    private EffectFactory() {
    }

    private static void addEffect(Effect effect) {
        Static.effects.add(effect);
    }

    public static void createPopText(MapObject obj, String text) {
        createPopText(obj, text, Colors.text_default.color);
    }

    public static void createPopText(MapObject obj, String text, Color color) {
        E_PopText effect = new E_PopText();
        effect.setGridPos(obj.getGx(), obj.getGy());
        effect.setText(text);
        effect.setColor(color);
        addEffect(effect);
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

    public static void createXpText(MapObject obj, String amount) {
        E_PopText effect = new E_PopText();
        effect.setGridPos(obj.getGx(), obj.getGy());
        effect.setText("+" + amount + " xp");
        effect.setColor(Colors.xp.color);
        addEffect(effect);
    }

    public static void createGoldText(MapObject obj, String amount) {
        E_PopText effect = new E_PopText();
        effect.setGridPos(obj.getGx(), obj.getGy());
        effect.setText("+" + amount + " gold");
        effect.setColor(Colors.yellow.color);
        addEffect(effect);
    }
}
