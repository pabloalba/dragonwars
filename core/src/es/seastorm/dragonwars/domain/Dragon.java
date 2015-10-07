package es.seastorm.dragonwars.domain;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;

import java.util.Stack;

import es.seastorm.dragonwars.DragonWarsGame;

public class Dragon extends Item {
    public Stack<Button> movementList = new Stack<Button>();
    Fire fire;
    boolean showFire;
    public int owner = 0;
    public int life = 1;
    public int maxLife = 1;
    public String dragonName;
    public String title;

    public boolean isShowFire() {
        return showFire;
    }

    public void setShowFire(boolean showFire) {
        this.showFire = showFire;
    }

    public Dragon(int owner, String image, Float x, Float y, Float angle, String fireImage, Integer life, String title) {
        super(image+"-128.png", x, y, angle);
        dragonName = image;
        fire = new Fire(fireImage, x, y, angle);
        this.owner = owner;
        this.life = life;
        this.maxLife = life;
        this.title = title;
    }


    public void startNextMovemet() {
        Button movement = movementList.pop();
        moveDragon(movement);
    }


    public void moveDragon(Button button) {
        this.setAngle(button.incRadius);
        this.setFaceAngle(this.faceAngle + button.incRadius);
        speedX = button.speedX;
        speedY = button.speedY;
        speedAngle = button.speedAngle;

        Float scaleX = (float) Math.cos(Math.toRadians(this.getFaceAngle()));
        Float scaleY = (float) Math.sin(Math.toRadians(this.getFaceAngle()));


        Float calcX = Math.max(0f, Math.min(DragonWarsGame.SCREEN_X - this.getWidth(), this.getX() + button.incX * scaleX));

        Float calcY = Math.max(DragonWarsGame.SKY_LIMIT, Math.min(DragonWarsGame.SCREEN_Y - this.getHeight(), this.getY() + button.incX * scaleY));

        this.setNextX(calcX);
        this.setNextY(calcY);
    }


    public void rotateAndDrawFire(SpriteBatch batch, Float delta) {

        if (isShowFire()) {
            Float posX = getX();
            Float posY = getY();

            if (getFaceAngle() == 0) {
                posX += getWidth();
                posY -= getHeight() / 2;
            } else if (getFaceAngle() == 45) {
                posX += getWidth() / 2;
                posY += getHeight() / 2;
            } else if (getFaceAngle() == 90) {
                posX -= getWidth() / 2;
                posY += getHeight();
            } else if (getFaceAngle() == 135) {
                posX -= (getWidth()/2) * 3;
                posY += getHeight()/2;
            } else if (getFaceAngle() == 180) {
                posX -= getWidth() * 2;
                posY -= getHeight() / 2;
            } else if (getFaceAngle() == 225) {
                posX -= (getWidth()/2) * 3;
                posY -= (getWidth()/2) * 3;
            }else if (getFaceAngle() == 270) {
                posX -= getWidth() / 2;
                posY -= getHeight() * 2;
            } else if (getFaceAngle() == 315) {
                posX += getWidth() / 2;
                posY -= (getWidth()/2) * 3;
            }


            fire.setX(posX);
            fire.setY(posY);

            if (fire.faceAngle != faceAngle) {
                fire.setAngle(faceAngle - fire.faceAngle);
            }
            fire.setAngleAndDraw(batch, delta);
        }


    }

    public void test() {
        setShowFire(true);
        Float posX = getX();
        Float posY = getY();

        if (getFaceAngle() == 0) {
            posX += getWidth() - 20;
            posY -= getHeight() / 2;
        } else if (getFaceAngle() == 90) {
            posX -= getWidth() / 2;
            posY += getHeight();
        } else if (getFaceAngle() == 180) {
            posX -= getWidth() - 20;
            posY -= getHeight() / 2;
        } else if (getFaceAngle() == 270) {
            posX += getWidth() / 2;
            posY += getHeight() - 20;
        }


        System.out.println(getWidth() + ", " + getHeight() + "     -    " + getX() + ", " + getY() + "      -       " + posX + ", " + posY + "      -       " + getFaceAngle());

    }
    
    
    
    public Polygon getFireTriangle(){

        Float cx = this.getX() + (this.getWidth()/2);
        Float cy = this.getY() + (this.getHeight()/2);
        float r = 40;

        Float scaleX = (float) Math.cos(Math.toRadians(this.getFaceAngle()));
        Float scaleY = (float) Math.sin(Math.toRadians(this.getFaceAngle()));

        float p1x = cx + r * scaleX;
        float p1y = cy + r * scaleY;


        r = 256;
        scaleX = (float) Math.cos(Math.toRadians(this.getFaceAngle() - 23));
        scaleY = (float) Math.sin(Math.toRadians(this.getFaceAngle() - 23));

        float p2x = p1x + r * scaleX;
        float p2y = p1y + r * scaleY;


        scaleX = (float) Math.cos(Math.toRadians(this.getFaceAngle() + 23));
        scaleY = (float) Math.sin(Math.toRadians(this.getFaceAngle() + 23));

        float p3x = p1x + r * scaleX;
        float p3y = p1y + r * scaleY;

        return new Polygon(new float[] {p1x, p1y, p2x, p2y, p3x, p3y});
    }


}
