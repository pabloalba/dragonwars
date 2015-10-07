package es.seastorm.dragonwars.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.seastorm.dragonwars.domain.Dragon;
import es.seastorm.dragonwars.domain.Item;
import es.seastorm.dragonwars.domain.Picture;

public class PopupInfoDragonScreen extends PopupScreen{
    Dragon dragon;

    public PopupInfoDragonScreen(){
        super();
    }

    public void setDragon(Dragon dragon){
        items.clear();
        Picture picture = new Picture(dragon.dragonName+".png", 300f, 1100f, 0f);
        items.add(picture);
        this.dragon = dragon;

        float posX = 704 - (100 * (dragon.maxLife-1));

        for (int i = 0; i < dragon.maxLife; i++) {
            if (i<=dragon.life-1) {
                items.add(new Picture("heart.png", posX, 800f, 0f));
            } else {
                items.add(new Picture("heart_empty.png", posX, 800f, 0f));
            }
            posX += 200;
        }
    }


    public void draw(SpriteBatch batch, float delta){
        super.draw(batch, delta);
        font.draw(batch, dragon.title, 350, 2100);

    }
}
