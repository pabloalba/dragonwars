package es.seastorm.dragonwars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import es.seastorm.dragonwars.domain.Item;

public abstract class Screen {
    String name;
    Texture background;
    public Array<Item> items = new Array<Item>();
    protected Float baseX = 0f;
    protected Float baseY = 0f;


    public void draw(SpriteBatch batch, float delta){
        if (background != null) {
            batch.draw(background, baseX, baseY);
        }

        for (Item item : items) {
            item.rotateAndDraw(batch, delta);
        }

    }

}
