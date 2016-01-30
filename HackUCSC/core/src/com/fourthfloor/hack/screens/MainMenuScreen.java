package com.fourthfloor.hack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.fourthfloor.hack.MainCore;

/**
 * Created by Carson on 1/29/2016.
 */
public class MainMenuScreen implements Screen {

    MainCore core;

    Stage stage;
    Image logo;
    Table list;
    TextButton employeesButton;
    TextButton statisticsButton;
    TextButton employerButton;
    TextButton settingsButton;

    public MainMenuScreen(MainCore mainCore) {
        this.core = mainCore;

        stage = new Stage();
        logo = new Image(new Texture("test.png"));
        list = new Table();

        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.down = patch;
        style.up = patch;
        style.font = new BitmapFont(Gdx.files.internal("Arial2.fnt"), Gdx.files.internal("Arial2_0.png"), false);
        style.fontColor = new Color(0.75f, 0.75f, 0.75f, 1.0f);


        employeesButton = new TextButton("Employees", style);
        statisticsButton = new TextButton("Statistics", style);
        employerButton = new TextButton("Employer", style);
        settingsButton = new TextButton("Settings", style);

        list.align(Align.topLeft);

        list.setFillParent(true);
        list.add(logo).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 4);
        list.row();
        list.add(employeesButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() * 3 / 16);
        list.row();
        list.add(statisticsButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() * 3 / 16);
        list.row();
        list.add(employerButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() * 3 / 16);
        list.row();
        list.add(settingsButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() * 3 / 16);
        Gdx.app.log("Height", ""+(Gdx.graphics.getHeight()*3/16));

        Gdx.input.setInputProcessor(stage);
        stage.addActor(list);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
