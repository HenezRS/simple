package com.henez.simple.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.henez.simple.enums.Colors;

public class Text {

    public static final int TEXT_H = 5;
    public static final int TEXT_LINE_H = 7;
    public static final int TEXT_DOTPOINT_H = 9;
    public static final int TEXT_PARAGRAPH_H = 12;
    private BitmapFont text;
    private BitmapFont textNotFlipped;
    private Color colorDefault = Colors.text_default.color;
    private Color colorNext = colorDefault;

    public Text() {

    }

    public void load() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/HenezTheBestFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        parameter.flip = true;
        text = generator.generateFont(parameter);
        text.getData().setLineHeight(TEXT_LINE_H);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        parameter.flip = false;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/HenezTheBestFont.ttf"));
        textNotFlipped = generator.generateFont(parameter);
        textNotFlipped.getData().setLineHeight(TEXT_LINE_H);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public void draw(Batch batch, CharSequence str, float x, float y) {
        text.setColor(colorDefault);
        text.draw(batch, str.toString().toUpperCase(), x, y);
    }

    public void drawRight(Batch batch, CharSequence str, float x, float y) {
        text.setColor(colorDefault);
        text.draw(batch, str.toString().toUpperCase(), x + 1, y, 0, Align.right, true);
    }

    public GlyphLayout draw(Batch batch, CharSequence str, float x, float y, int width, boolean wrap) {
        text.setColor(colorDefault);
        return text.draw(batch, str.toString().toUpperCase(), x + 1, y, width, Align.left, wrap);
    }

    public GlyphLayout getGlyphLayout(CharSequence str, int width, boolean wrap) {
        return new GlyphLayout(textNotFlipped, str, colorDefault, width, Align.left, wrap);
    }

    public void draw(Batch batch, CharSequence str, float x, float y, float a) {
        text.setColor(colorDefault.r, colorDefault.g, colorDefault.b, a);
        text.draw(batch, str.toString().toUpperCase(), x, y);
    }

    public void draw(Batch batch, CharSequence str, float x, float y, int line) {
        text.setColor(colorDefault);
        text.draw(batch, str.toString().toUpperCase(), x, y + getLineHeightBy(line));
    }

    public void draw(Batch batch, CharSequence str, float x, float y, int line, int paragraph) {
        text.setColor(colorDefault);
        text.draw(batch, str.toString().toUpperCase(), x, y + getLineHeightBy(line) + getParagraphHeightBy(paragraph));
    }

    public void draw(Batch batch, CharSequence str, float x, float y, Color color) {
        text.setColor(color);
        text.draw(batch, str.toString().toUpperCase(), x, y);
        text.setColor(colorDefault);
    }

    public void draw(Batch batch, CharSequence str, float x, float y, TextStyle style) {
        switch (style) {
        case shadow:
            draw(batch, str, x + 1, y + 1, Color.BLACK);
            draw(batch, str, x, y, colorNext);
            break;
        default:
            draw(batch, str, x, y, colorNext);
            break;
        }
    }

    public void draw(Batch batch, CharSequence str, float x, float y, Color color, TextStyle style) {
        text.setColor(color);
        draw(batch, str, x, y, style);
        text.setColor(colorDefault);
    }

    public Rectangle getTextRect(String line) {
        GlyphLayout gl = new GlyphLayout();
        gl.setText(text, line);
        return new Rectangle(0, 0, gl.width, TEXT_H);
    }

    public int getLineHeightBy(int mul) {
        return TEXT_LINE_H * mul;
    }

    public int getParagraphHeightBy(int mul) {
        return TEXT_PARAGRAPH_H * mul;
    }

    public enum TextStyle {
        none, shadow,
        ;
    }
}
