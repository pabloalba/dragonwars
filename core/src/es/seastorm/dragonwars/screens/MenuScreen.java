package es.seastorm.dragonwars.screens;

import com.badlogic.gdx.graphics.Texture;

import es.seastorm.dragonwars.domain.Picture;
import es.seastorm.dragonwars.utils.ItemsIds;
import es.seastorm.dragonwars.utils.Translator;

public class MenuScreen extends Screen {

    public MenuScreen() {
        name = "MenuScreen";
        background = new Texture("menu.jpg");

        items.add(new Picture("menu_howtoplay.png", ItemsIds.MENU_HOW_TO_PLAY, 350f, 1400f));
        items.add(new Picture("menu_tutorial.png", ItemsIds.MENU_TUTORIAL, 350f, 1200f));
        items.add(new Picture("menu_one_player.png", ItemsIds.MENU_ONE_PLAYER, 350f, 1000f));
        items.add(new Picture("menu_two_player.png", ItemsIds.MENU_TWO_PLAYERS, 350f, 800f));
        items.add(new Picture("menu_settings.png", ItemsIds.MENU_SETTINGS, 350f, 600f));
    }
}
