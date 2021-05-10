package com.henez.simple.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.henez.simple.Static;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.global.Global;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class In {
    public static Key up = new Key("UP");
    public static Key down = new Key("DOWN");
    public static Key left = new Key("LEFT");
    public static Key right = new Key("RIGHT");
    public static Key esc = new Key("ESC");
    public static Key ctrl = new Key("CTRL");
    public static Key space = new Key("SPACE");
    public static Key tab = new Key("TAB");
    public static Mouse mouse = new Mouse();

    public static Key skill1 = new Key("Q");
    public static Key skill2 = new Key("W");
    public static Key skill3 = new Key("E");
    public static Key skill4 = new Key("R");

    public static GameList<Key> keys;
    public static GameList<Key> skillKeys;

    public In() {
        keys = Stream.of(right, up, left, down, esc, ctrl, space,
                         tab, skill1, skill2, skill3, skill4).collect(Collectors.toCollection(GameList::new));
        skillKeys = Stream.of(skill1, skill2, skill3, skill4).collect(Collectors.toCollection(GameList::new));
    }

    public static String showHeld() {
        StringBuilder held = new StringBuilder("[ ");
        keys.stream().filter(Key::isHeld).forEach(key -> held.append(key.getName()).append(" "));
        held.append("]");

        return held.toString();
    }

    public void capture() {
        set(up, Input.Keys.W);
        set(down, Input.Keys.S);
        set(left, Input.Keys.A);
        set(right, Input.Keys.D);

        set(ctrl, Input.Keys.CONTROL_LEFT);
        set(esc, Input.Keys.ESCAPE);
        set(space, Input.Keys.SPACE);
        set(tab, Input.Keys.TAB);

        set(skill1, Input.Keys.Q);
        set(skill2, Input.Keys.W);
        set(skill3, Input.Keys.E);
        set(skill4, Input.Keys.R);

        mouse.setClicked(Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.LEFT));
        mouse.setHeld(Gdx.input.isButtonPressed(Input.Buttons.LEFT));
        boolean wasClicked = mouse.isClicked();
        mouse.setReleased(!(Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && wasClicked));

        mouse.setClicked2(Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.RIGHT));
        mouse.setHeld2(Gdx.input.isButtonPressed(Input.Buttons.RIGHT));
        wasClicked = mouse.isClicked2();
        mouse.setReleased2(!(Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && wasClicked));

        mouse.setPos(Gdx.input.getX() / Global.gameScale, Gdx.input.getY() / Global.gameScale);
        mouse.setPosWorld((Gdx.input.getX() / Global.gameScale) + Static.renderer.getX(), (Gdx.input.getY() / Global.gameScale) + Static.renderer.getY());
        mouse.setPosGrid(mouse.getWx() / Global.tilePixelSize, mouse.getWy() / Global.tilePixelSize);
    }

    private void set(Key key, int code) {
        boolean wasPressed = key.isPressed();
        key.set(Gdx.input.isKeyJustPressed(code), Gdx.input.isKeyPressed(code), !Gdx.input.isKeyPressed(code) && wasPressed);
    }

    public static String getSkillKeyNameByIndex(int i) {
        return skillKeys.get(i).getName();
    }
}
