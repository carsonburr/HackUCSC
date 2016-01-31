package com.fourthfloor.hack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
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

        Database.database.add(new Employee(99999, "Carson", "so many things", "PersonPlaceholder.png"));
        Database.database.add(new Employee(60000, "Thomas", "not as many things", "PersonPlaceholder.png"));
        Database.database.add(new Employee(50000, "Kyle", "so many things", "PersonPlaceholder.png"));
        Database.database.add(new Employee(70000, "Milla", "so many things", "PersonPlaceholder.png"));
        Database.database.get(0).clock("00:00:00");
        Database.database.get(1).clock("00:00:00");
        Database.database.get(2).clock("00:00:00");
        Database.database.get(3).clock("00:00:00");
        Database.database.get(0).clock("16:00:00");
        Database.database.get(1).clock("23:00:00");
        Database.database.get(2).clock("04:00:00");
        Database.database.get(3).clock("11:00:00");

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        table = new Table();
        stage.addActor(table);

        image = new Image(new Texture("HackUCSC_Logo_AT.png"));

        table.setFillParent(true);
        table.setBackground(new NinePatchDrawable(new NinePatch(new Texture("TitleBar.png"), 1, 1, 1, 1)));

        table.add(image);

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
