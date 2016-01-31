package com.fourthfloor.hack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.fourthfloor.hack.MainCore;
import com.fourthfloor.hack.utils.Database;
import com.fourthfloor.hack.utils.Employee;

/**
 * Created by Carson on 1/29/2016.
 */
public class SplashScreen implements Screen{

    final MainCore core;

    private Stage stage;
    private Table table;
    private Image image;

    public SplashScreen(MainCore mainCore) {
        this.core = mainCore;

        Database.database.add(new Employee(9999, "Carson", "so many things", "PersonPlaceholder.png"));
        Database.database.add(new Employee(9999, "Thomas", "not as many things", "PersonPlaceholder.png"));
        Database.database.add(new Employee(9999, "Kyle", "so many things", "PersonPlaceholder.png"));
        Database.database.add(new Employee(9999, "Milla", "so many things", "PersonPlaceholder.png"));

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        table = new Table();
        stage.addActor(table);

        image = new Image(new Texture("test.png"));

        table.setFillParent(true);

        table.add(image).width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight()).bottom().right();

        Timer.schedule(new Task(){
            @Override
            public void run() {
                core.setScreen(new MainMenuScreen(core));
                dispose();
            }
        }, 1.0f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
