package es.seastorm.dragonwars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import es.seastorm.dragonwars.screens.Screen;

public class DragonWarsGame extends ApplicationAdapter {
    public static Integer SCREEN_X = 1536;
    public static Integer SCREEN_Y = 2560;
    public static Integer SKY_LIMIT = 210;

    private Integer SIZE_X = 128;
    private Integer SIZE_Y = 128;

    private Float BUTTON_SIZE_X = 150f;
    private Float BUTTON_SIZE_Y = 150f;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    Viewport viewport;

    String debug = "";
    Long lastClickTime = 0L;

    Vector3 touchPos = new Vector3();


    private BitmapFont font;



    DragonWarsEngine dragonWarsEngine;

    @Override
    public void create() {
        System.out.println("Create...");
        dragonWarsEngine = new DragonWarsEngine(this);

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, SCREEN_X, );



        camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_X,SCREEN_Y,camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);



        batch = new SpriteBatch();

    }


    @Override
    public void render() {
        Float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isTouched()) {
            _touchButton();
        }

        dragonWarsEngine.tick(delta);


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (Screen screen : dragonWarsEngine.getScreensToShow()) {
            screen.draw(batch, delta);
        }




        batch.end();


    }

    private void _touchButton() {
        Long time = System.currentTimeMillis();
        if (time - lastClickTime > 200) {
            lastClickTime = time;

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPos = viewport.unproject(touchPos);
            dragonWarsEngine.touch(touchPos.x, touchPos.y);

        }
    }


    @Override
    public void dispose(){
        //TODO: Dispose screen textures
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }


}
