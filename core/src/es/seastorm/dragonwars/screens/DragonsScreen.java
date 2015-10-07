package es.seastorm.dragonwars.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.seastorm.dragonwars.DragonWarsGame;
import es.seastorm.dragonwars.domain.Dragon;
import es.seastorm.dragonwars.domain.Item;

public class DragonsScreen extends Screen {
    public DragonsScreen() {
        name = "DragonsScreen";
        this.baseY = (float) DragonWarsGame.SKY_LIMIT;
        background = new Texture("clouds.jpg");

        items.add(new Dragon(0, "dragon-red", (float) ((DragonWarsGame.SCREEN_X - 128) / 2), baseY + 50, 0f, "fire-red-256.png", 3, "Red Dragon"));
        items.add(new Dragon(1, "dragon-green", (float) ((DragonWarsGame.SCREEN_X - 128) / 2)-200, ((float) (DragonWarsGame.SCREEN_Y - 128 - 50)), 180f, "fire-green-256.png", 1, "Green Dragon"));
        items.add(new Dragon(1, "dragon-green", (float) ((DragonWarsGame.SCREEN_X - 128) / 2)+200, ((float) (DragonWarsGame.SCREEN_Y - 128 - 50)), 180f, "fire-green-256.png", 1, "Green Dragon"));

    }

    public boolean itemsStopped() {
        for (int i = 0; i < items.size; i++) {
            if (!(items.get(i)).stopped()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        super.draw(batch, delta);

        for (Item item : items) {
            ((Dragon) item).rotateAndDrawFire(batch, delta);
        }

    }
}
