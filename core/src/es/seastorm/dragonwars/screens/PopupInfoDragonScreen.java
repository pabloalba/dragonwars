package es.seastorm.dragonwars.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.seastorm.dragonwars.domain.Dragon;
import es.seastorm.dragonwars.domain.Item;
import es.seastorm.dragonwars.domain.Picture;
import es.seastorm.dragonwars.domain.Text;
import es.seastorm.dragonwars.utils.CommonResources;

public class PopupInfoDragonScreen extends PopupScreen{
    Dragon dragon;

    public PopupInfoDragonScreen(){
        super();
    }

    public void setDragon(Dragon dragon){
        items.clear();
        items.add(new Text(dragon.title, 350f, 2100f));

        Picture picture = new Picture(dragon.dragonName+".png", 300f, 1100f);
        items.add(picture);
        this.dragon = dragon;

        float posX = 704 - (100 * (dragon.maxLife-1));

        for (int i = 0; i < dragon.maxLife; i++) {
            if (i<=dragon.life-1) {
                items.add(new Picture("heart.png", posX, 800f));
            } else {
                items.add(new Picture("heart_empty.png", posX, 800f));
            }
            posX += 200;
        }
    }

/*
    public void draw(SpriteBatch batch, float delta){
        super.draw(batch, delta);
        CommonResources.font128.draw(batch, dragon.title, 350, 2100);

    }*/
}
