package com.potter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameplayScreen implements Screen {

    private HarryPotter harryPotter;
    private OrthographicCamera camera;
    private Stage stage;
    private Texture backgroundTexture;
    private SpriteBatch batch;


    public GameplayScreen(HarryPotter harryPotter) {
        this.harryPotter = harryPotter;
        batch = harryPotter.getSpriteBatch();
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new ScreenViewport(camera), batch);
        camera.setToOrtho(false, Graphic.SCREEN_WIDTH, Graphic.SCREEN_HEIGHT );
        Gdx.input.setInputProcessor(stage);
        backgroundTexture = new Texture(Gdx.files.internal("boardMini.jpg"));
        HandGroup hg = new HandGroup();
        hg.setPosition(250,0);
        for (int i = 0; i < 5; i++) {
            CardActor cardActor = new CardActor();
            hg.addCard(cardActor);
        }
        stage.addActor(hg);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.draw(backgroundTexture,0,150, Graphic.SCREEN_WIDTH, (Graphic.SCREEN_HEIGHT)-150);
        batch.end();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
