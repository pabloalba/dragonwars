package es.seastorm.dragonwars.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Item extends Sprite {
    public Integer id;

    Item(Texture texture){
        super(texture);
    }


    public void rotateAndDraw(SpriteBatch batch, Float delta) {
        this.draw(batch);
    }

    public boolean stopped() {
        return true;
    }

    public Item(Float x, Float y) {
        setX(x);
        setY(y);
    }

    public Item(Texture image, Float x, Float y) {
        super(image);
        setX(x);
        setY(y);
    }

    public Item(String image, Float x, Float y) {
        this(new Texture(image), x, y);
    }

    public Item(String image, Integer id, Float x, Float y) {
        this(new Texture(image), x, y);
        this.id = id;
    }

}
