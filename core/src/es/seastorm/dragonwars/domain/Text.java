package es.seastorm.dragonwars.domain;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.seastorm.dragonwars.utils.CommonResources;

public class Text extends Item {
    protected String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Text(String text, Float x, Float y){
        super(x, y);
        setText(text);
    }


    public void rotateAndDraw(SpriteBatch batch, Float delta) {
        CommonResources.getFont128().draw(batch, text, getX(), getY());
    }
}
