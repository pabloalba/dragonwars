package es.seastorm.dragonwars;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import es.seastorm.dragonwars.domain.Item;

public class Utils {

    public static Sprite touchedSprite(Array<Item> sprites, float x, float y){
        for (int i = 0; i < sprites.size; i++) {
            Sprite sprite = sprites.get(i);
            if (sprite.getBoundingRectangle().contains(x, y)) {
                return  sprite;
            }
        }
        return null;
    }


}
