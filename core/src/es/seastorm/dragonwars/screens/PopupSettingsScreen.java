package es.seastorm.dragonwars.screens;

import es.seastorm.dragonwars.domain.Picture;

public class PopupSettingsScreen extends PopupScreen {
    public static String SOUND_ON = "sound_on.png";
    public static String SOUND_OFF = "sound_off.png";

    public PopupSettingsScreen() {
        super();
    }

    public void setSound(Boolean musicOn) {
        items.clear();
        if (musicOn) {
            items.add(new Picture(SOUND_OFF, 300f, 1100f));
        } else {
            items.add(new Picture(SOUND_ON, 300f, 1100f));
        }
    }

}
