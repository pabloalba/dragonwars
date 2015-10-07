package es.seastorm.dragonwars;

import es.seastorm.dragonwars.domain.Button;
import es.seastorm.dragonwars.domain.Dragon;
import es.seastorm.dragonwars.screens.MovementButtonsScreen;

public class DragonIa {
    public static Button getValidButton(Dragon dragon, MovementButtonsScreen movementButtonsScreen) {
        Button button = movementButtonsScreen.getRandomButton();
        int tries = 0;
        while (tries < 10 && !isValidButton(dragon, button)) {
            button = movementButtonsScreen.getRandomButton();
            tries++;
        }
        if (tries == 10){
            button = movementButtonsScreen.getLastButton();
        }
        return button;
    }


    private static boolean isValidButton(Dragon dragon, Button button) {
        Float faceAngle = dragon.getFaceAngle() + button.incRadius;


        Float scaleX = (float) Math.cos(Math.toRadians(faceAngle));
        Float scaleY = (float) Math.sin(Math.toRadians(faceAngle));

        float calcX = dragon.getX() + button.incX * scaleX;
        float calcY = dragon.getY() + button.incX * scaleY;

        return (
                (calcX > 0) &&
                        (calcX < (DragonWarsGame.SCREEN_X - dragon.getWidth())) &&
                        (calcY > DragonWarsGame.SKY_LIMIT) &&
                        (calcY < (DragonWarsGame.SCREEN_Y - dragon.getHeight()))
        );
    }
}
