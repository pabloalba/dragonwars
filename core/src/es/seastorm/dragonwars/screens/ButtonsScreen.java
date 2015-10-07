package es.seastorm.dragonwars.screens;

import com.badlogic.gdx.graphics.Texture;

import es.seastorm.dragonwars.domain.Button;

public class ButtonsScreen extends Screen {
    public static String GEAR = "gear.png";
    public static String PAPIRE1 = "papire1.png";
    public static String PAPIRE2 = "papire2.png";
    public static String PAPIRE3 = "papire3.png";
    public static String OK = "ok.png";

    private Float[] buttonsX = {443f, 693f, 943f, 1343f, 50f};



    Button papire1 = new Button(buttonsX[0], 0f, PAPIRE1);
    Button papire2 = new Button(buttonsX[1], 0f, PAPIRE2);
    Button papire3 = new Button(buttonsX[2], 0f, PAPIRE3);
    Button ok = new Button(buttonsX[3], 0f, OK);

    Button gear = new Button(buttonsX[4], 0f, GEAR);

    public ButtonsScreen() {
        name = "ButtonsScreen";
        resetMovementButtons();
        background = new Texture("wood.jpg");
    }

    public void addMovementButton(Integer index, Button button) {
        button.setX(buttonsX[index]);
        items.removeIndex(index);
        items.insert(index, button);
    }


    public void resetMovementButtons() {
        items.clear();
        items.add(papire1);
        items.add(papire2);
        items.add(papire3);
        items.add(ok);
        items.add(gear);
    }

}
