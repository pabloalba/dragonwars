package es.seastorm.dragonwars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PopupScreen extends Screen{
    public static Texture popupBackground = new Texture("popup.png");


    public PopupScreen() {
        background = popupBackground;

    }

}
