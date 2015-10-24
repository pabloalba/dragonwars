package es.seastorm.dragonwars.domain;


public class Picture extends Item {
    protected String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    public Picture(String image, Float x, Float y){
        super(image, x, y);
        setPicture(image);
    }

    public Picture(String image, Integer id, Float x, Float y){
        super(image, id, x, y);
        setPicture(image);
    }
}
