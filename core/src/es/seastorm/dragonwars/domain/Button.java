package es.seastorm.dragonwars.domain;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button extends Item {

    public float incX;
    public float incRadius;
    public Integer speedX;
    public Integer speedY;
    public Integer speedAngle;

    public Button(Float x, Float y, Float incX, Float incRadius, Integer speedX, Integer speedY, Integer speedAngle, String picture) {
        super(picture, x, y, 0f);
        this.picture = picture;
        this.incX = incX;
        this.incRadius = incRadius;
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedAngle = speedAngle;
    }

    public Button(Float x, Float y, String picture) {
        super(picture, x, y, 0f);
        this.picture = picture;
        this.incX = 0;
        this.incRadius = 0;
        this.speedX = 0;
        this.speedY = 0;
        this.speedAngle = 0;
    }





    public Button clone(){
        return new Button(getX(), getY(), incX, incRadius, speedX, speedY, speedAngle, picture);
    }





}
