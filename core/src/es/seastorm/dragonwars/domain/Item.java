package es.seastorm.dragonwars.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Item extends Sprite {

    Float angle = 0f;
    Float faceAngle = 90f;

    Integer speedX = 200;
    Integer speedY = 200;

    Integer speedAngle = 90;

    protected String picture;

    private float nextX;
    private float nextY;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getNextX() {
        return nextX;
    }

    public void setNextX(float nextX) {
        this.nextX = nextX;
    }

    public float getNextY() {
        return nextY;
    }

    public void setNextY(float nextY) {
        this.nextY = nextY;
    }

    public Float getAngle() {
        return angle;
    }

    public void setAngle(Float angle) {
        this.angle = angle;

        if (this.angle < 0) {
            this.angle += 360;
        }
        if (this.angle >= 360) {
            this.angle -= 360;
        }
    }

    public void setFaceAngle(Float faceAngle) {
        this.faceAngle = faceAngle;

        if (this.faceAngle < 0) {
            this.faceAngle += 360;
        }
        if (this.faceAngle >= 360) {
            this.faceAngle -= 360;
        }
    }

    public Float getFaceAngle() {
        return faceAngle;
    }

    public void rotateAndDraw(SpriteBatch batch, Float delta) {
        Float amount = speedAngle * delta;
        if (angle < 180) {
            amount = Math.min(amount, angle);
        } else {
            amount = -Math.min(amount, (angle + 180));
        }

        this.rotate(amount);
        setAngle(angle - amount);
        this.draw(batch);
    }

    public void setAngleAndDraw(SpriteBatch batch, Float delta) {
        this.setFaceAngle(this.faceAngle + angle);
        this.rotate(angle);

        setAngle(0f);
        this.draw(batch);
    }

    public Item(String image, Float x, Float y, Float angle) {
        super(new Texture(image));
        initialize(x, y, angle);
        this.picture = image;
    }

    public Item(Texture image, Float x, Float y, Float angle) {
        super(image);
        initialize(x, y, angle);
    }

    private void initialize(Float x, Float y, Float angle) {
        setX(x);
        setY(y);
        setNextX(x);
        setNextY(y);

        this.setFaceAngle(this.faceAngle + angle);
        this.rotate(angle);

        setAngle(0f);
    }


    public void updatePosition(Float time) {
        if (getX() > nextX) {
            setX(Math.max((getX() - speedX * time), nextX));
        } else if (getX() < nextX) {
            setX(Math.min((getX() + speedX * time), nextX));
        }

        if (getY() > nextY) {
            setY(Math.max((getY() - speedY * time), nextY));
        } else if (getY() < nextY) {
            setY(Math.min((getY() + speedY * time), nextY));
        }
    }

    public boolean stopped() {
        return (
                (getX() == nextX) &&
                        (getY() == nextY) &&
                        (getAngle() == 0)
        );
    }

}
