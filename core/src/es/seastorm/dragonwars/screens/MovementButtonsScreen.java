package es.seastorm.dragonwars.screens;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import es.seastorm.dragonwars.domain.Button;

public class MovementButtonsScreen extends Screen {
    public static String ARROWL = "arrowL.png";
    public static String ARROWR = "arrowR.png";


    Button button1 = new Button(0f, 0f, 100f, 0f, 200, 200, 90, "button1S.png");
    Button button2 = new Button(0f, 0f, 200f, 0f,  200, 200, 90, "button2S.png");
    Button button3 = new Button(0f, 0f, 200f, 45f,  200, 200, 90, "button1SL.png");
    Button button4 = new Button(0f, 0f, 200f, -45f,  200, 200, 90, "button1SR.png");
    Button button5 = new Button(0f, 0f, -200f, 180f,  200, 200, 360, "buttonF.png");

    private Button[] buttons = {button1, button2, button3, button4, button5};

    private Random random = new Random();

    public Button getRandomButton(){
        return buttons[random.nextInt(buttons.length)].clone();
    }


    public MovementButtonsScreen() {
        name = "MovementButtonsScreen";
        background = new Texture("wood.jpg");
        int pos = 200;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setX(pos);
            items.add(buttons[i]);
            pos += 200;
        }
    }

    public Button getLastButton(){
        return buttons[buttons.length -1];
    }


}
