package es.seastorm.dragonwars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

import es.seastorm.dragonwars.domain.Button;
import es.seastorm.dragonwars.domain.Dragon;
import es.seastorm.dragonwars.domain.Item;
import es.seastorm.dragonwars.domain.Picture;
import es.seastorm.dragonwars.screens.ButtonsScreen;
import es.seastorm.dragonwars.screens.DragonsScreen;
import es.seastorm.dragonwars.screens.MovementButtonsScreen;
import es.seastorm.dragonwars.screens.PopupInfoDragonScreen;
import es.seastorm.dragonwars.screens.PopupSettingsScreen;
import es.seastorm.dragonwars.screens.Screen;

public class DragonWarsEngine {
    public final int MODE_SELECT_MOVE_NUM = 0;
    public final int MODE_SELECT_MOVE = 1;
    public final int MODE_MOVE_READY = 2;
    public final int MODE_MOVING_DRAGONS = 3;
    public final int MODE_FIRING = 4;
    public final int MODE_MOVING_FIRE = 5;
    public final int MODE_POPUP_INFO_DRAGON = 6;
    public final int MODE_POPUP_SETTINGS = 7;

    ButtonsScreen buttonsScreen;
    DragonsScreen dragonsScreen;
    MovementButtonsScreen movementButtonsScreen;
    PopupInfoDragonScreen popupInfoDragonScreen;
    PopupSettingsScreen popupSettingsScreen = new PopupSettingsScreen();

    HashMap<Integer, Screen[]> screensByMode;


    int mode = MODE_SELECT_MOVE_NUM;
    int oldMode = MODE_SELECT_MOVE_NUM;
    private DragonWarsGame game;

    int numMove;
    long musicId = -1;
    Music oggMusic;
    Long fireTime;

    public DragonWarsEngine(DragonWarsGame game) {
        this.game = game;
        this.resetGame();
    }

    private void startMusic() {
        if (oggMusic == null) {
            oggMusic = Gdx.audio.newMusic(Gdx.files.internal("music/valkyries.ogg"));
        }
        oggMusic.play();
        popupSettingsScreen.setSound(true);
    }

    private void stopMusic() {
        if (oggMusic != null) {
            oggMusic.pause();
            popupSettingsScreen.setSound(false);
        }
    }

    private void resetGame() {
        buttonsScreen = new ButtonsScreen();
        dragonsScreen = new DragonsScreen();
        movementButtonsScreen = new MovementButtonsScreen();
        popupInfoDragonScreen = new PopupInfoDragonScreen();
        screensByMode = null;
        mode = MODE_SELECT_MOVE_NUM;
        startMusic();
    }


    public void touch(float x, float y) {
        if (mode == MODE_SELECT_MOVE_NUM) {
            _touchOnModeMoveNum(x, y);
        } else if (mode == MODE_SELECT_MOVE) {
            _touchOnModeMove(x, y);
        } else if (mode == MODE_POPUP_INFO_DRAGON) {
            mode = oldMode;
        } else if (mode == MODE_POPUP_SETTINGS){
            _touchOnSettingsMove(x, y);
        }

    }

    private void _touchDragon(float x, float y) {
        Dragon dragon = (Dragon) Utils.touchedSprite(dragonsScreen.items, x, y);

        if (dragon != null) {
            popupInfoDragonScreen.setDragon(dragon);
            oldMode = mode;
            mode = MODE_POPUP_INFO_DRAGON;
        }
    }

    private void _showSettings() {
        mode = MODE_POPUP_SETTINGS;
    }


    private void _touchOnModeMoveNum(float x, float y) {
        Button button = (Button) Utils.touchedSprite(buttonsScreen.items, x, y);

        if (button != null) {
            System.out.println("" + button);
            if (button.getPicture().equals(ButtonsScreen.OK)) {
                _moveReady();
            } else if (button.getPicture().equals(ButtonsScreen.GEAR)) {
                _showSettings();
            } else {
                numMove = buttonsScreen.items.indexOf(button, true);
                this.mode = MODE_SELECT_MOVE;
            }
        } else {
            _touchDragon(x, y);


            //test
           /* Dragon dragon = ((Dragon)dragonsScreen.items.get(0));
            Dragon dragon2 = ((Dragon)dragonsScreen.items.get(1));
            dragon.setShowFire(true);
            dragon.setAngle(45f);
            dragon.setFaceAngle(dragon.getFaceAngle() + 45);

            float[] fireTriangle = dragon.getFireTriangle();

            Rectangle dragonRectangle = dragon2.getBoundingRectangle();

            Polygon p1 = new Polygon(fireTriangle);
            boolean intersects = isCollision(p1, dragonRectangle);


            System.out.println(intersects);*/


        }
    }

    private void _touchOnSettingsMove(float x, float y){
        Picture button = (Picture) Utils.touchedSprite(popupSettingsScreen.items, x, y);

        if (button != null) {
            System.out.println(button.getPicture());
            if (button.getPicture().equals(PopupSettingsScreen.SOUND_ON)) {
                startMusic();
            } else if (button.getPicture().equals(PopupSettingsScreen.SOUND_OFF)) {
                stopMusic();
            }
        } else {
            this.mode = MODE_SELECT_MOVE_NUM;
        }
    }

    private boolean dragonBurns(Dragon d1, Dragon d2) {
        return isCollision(d1.getFireTriangle(), d2.getBoundingRectangle());
    }

    private boolean isCollision(Polygon p, Rectangle r) {
        Polygon rPoly = new Polygon(new float[]{0, 0, r.width, 0, r.width,
                r.height, 0, r.height});
        rPoly.setPosition(r.x, r.y);
        if (Intersector.overlapConvexPolygons(rPoly, p))
            return true;
        return false;
    }

    private void _touchOnModeMove(float x, float y) {
        System.out.println("_touchOnModeMove");
        Button button = (Button) Utils.touchedSprite(movementButtonsScreen.items, x, y);
        if (button != null) {
            buttonsScreen.addMovementButton(numMove, button.clone());
            mode = MODE_SELECT_MOVE_NUM;
        }
    }

    public void moveItems(Float delta) {
        if (mode == MODE_MOVING_DRAGONS) {
            for (int i = 0; i < dragonsScreen.items.size; i++) {
                Dragon dragon = (Dragon) dragonsScreen.items.get(i);
                dragon.updatePosition(delta);
            }
        }
    }

    public void _endDragonsMove() {
        System.out.println("_endDragonsMove");
        buttonsScreen.resetMovementButtons();
        mode = MODE_SELECT_MOVE_NUM;
    }


    public Screen[] getScreensToShow() {
        if (screensByMode == null) {
            screensByMode = new HashMap<Integer, Screen[]>();
            screensByMode.put(MODE_SELECT_MOVE_NUM, new Screen[]{dragonsScreen, buttonsScreen});
            screensByMode.put(MODE_SELECT_MOVE, new Screen[]{dragonsScreen, movementButtonsScreen});
            screensByMode.put(MODE_POPUP_INFO_DRAGON, new Screen[]{dragonsScreen, buttonsScreen, popupInfoDragonScreen});
            screensByMode.put(MODE_POPUP_SETTINGS, new Screen[]{dragonsScreen, buttonsScreen, popupSettingsScreen});
            screensByMode.put(-1, new Screen[]{dragonsScreen});
        }

        if (screensByMode.containsKey(mode)) {
            return screensByMode.get(mode);
        } else {
            return screensByMode.get(-1);
        }
    }

    private void _moveReady() {
        System.out.println("_moveReady");

        Button button1 = (Button) buttonsScreen.items.get(0);
        Button button2 = (Button) buttonsScreen.items.get(1);
        Button button3 = (Button) buttonsScreen.items.get(2);

        if ((!button1.getPicture().equals(ButtonsScreen.PAPIRE1)) &&
                (!button2.getPicture().equals(ButtonsScreen.PAPIRE2)) &&
                (!button3.getPicture().equals(ButtonsScreen.PAPIRE3))) {

            Dragon dragon1 = (Dragon) dragonsScreen.items.get(0);


            dragon1.movementList.push(button3);
            dragon1.movementList.push(button2);
            dragon1.movementList.push(button1);

            //For 1 player
            for (int i = 0; i < dragonsScreen.items.size; i++) {
                Dragon dragon2 = (Dragon) dragonsScreen.items.get(i);
                if (dragon2.owner != 0) {
                    dragon2.movementList.push(DragonIa.getValidButton(dragon2, movementButtonsScreen));
                    dragon2.movementList.push(DragonIa.getValidButton(dragon2, movementButtonsScreen));
                    dragon2.movementList.push(DragonIa.getValidButton(dragon2, movementButtonsScreen));
                }
            }


            this.mode = MODE_MOVE_READY;
        }


    }


    public void checkChangeMode() {
        if (mode == MODE_MOVE_READY) {
            //With two players, wait for second player moves
            startModeMovingDragons();
        } else if (mode == MODE_MOVING_DRAGONS) {
            if (dragonsScreen.itemsStopped()) {
                startModeMovingFire();
            }
        } else if (mode == MODE_MOVING_FIRE) {
            if ((System.currentTimeMillis() - fireTime) > 1000) {
                boolean end = stopModeMovingFire();
                if (end) {
                    resetGame();
                } else {
                    startModeMovingDragons();
                }
            }
        }
    }

    public void startModeMovingDragons() {
        System.out.println("startModeMovingDragons");
        if (((Dragon) dragonsScreen.items.get(0)).movementList.size() > 0) {
            for (int i = 0; i < dragonsScreen.items.size; i++) {
                Dragon dragon = (Dragon) dragonsScreen.items.get(i);
                dragon.startNextMovemet();
            }
            mode = MODE_MOVING_DRAGONS;
        } else {
            _endDragonsMove();
        }
    }

    public void startModeMovingFire() {
        for (int i = 0; i < dragonsScreen.items.size; i++) {
            Dragon dragon = (Dragon) dragonsScreen.items.get(i);
            dragon.setShowFire(true);
        }
        fireTime = System.currentTimeMillis();
        mode = MODE_MOVING_FIRE;
    }


    public boolean stopModeMovingFire() {
        boolean end = false;
        Array<Item> toRemove = new Array<Item>();
        for (int i = 0; i < dragonsScreen.items.size; i++) {
            Dragon dragon = (Dragon) dragonsScreen.items.get(i);
            dragon.setShowFire(false);
            toRemove.addAll(checkHits(dragon));
        }
        dragonsScreen.items.removeAll(toRemove, true);


        int player0 = 0;
        int player1 = 0;
        for (int i = 0; i < dragonsScreen.items.size; i++) {
            Dragon dragon = (Dragon) dragonsScreen.items.get(i);
            if (dragon.owner == 0) {
                player0++;
            } else {
                player1++;
            }

        }
        return (player0 == 0 || player1 == 0);
    }

    private Array<Item> checkHits(Dragon dragon) {
        Array<Item> toRemove = new Array<Item>();
        for (int j = 0; j < dragonsScreen.items.size; j++) {
            Dragon dragon2 = (Dragon) dragonsScreen.items.get(j);
            if (dragon.owner != dragon2.owner) { //No friendly fire
                if (dragonBurns(dragon, dragon2)) {
                    dragon2.life--;
                    if (dragon2.life <= 0) {
                        toRemove.add(dragon2);
                    }
                }
            }
        }
        return toRemove;
    }

    public void tick(Float delta) {
        checkChangeMode();
        moveItems(delta);
    }


}
