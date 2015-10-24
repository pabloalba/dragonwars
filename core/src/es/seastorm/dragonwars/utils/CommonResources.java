package es.seastorm.dragonwars.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class CommonResources {
    private static BitmapFont  font128;


    public static BitmapFont getFont128(){
        if (font128==null){
            font128 = new BitmapFont(Gdx.files.internal("darkcrystal128.fnt"), false);
        }
        return font128;
    }

    public static void reset(){
        font128 = new BitmapFont(Gdx.files.internal("darkcrystal128.fnt"), false);
    }
}
