package com.fourthfloor.hack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.fourthfloor.hack.MainCore;
import com.fourthfloor.hack.utils.Database;
import com.fourthfloor.hack.utils.Employee;


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

    public MainMenuScreen(MainCore mainCore) {

        this.core = mainCore;

        StatisticsScreen.setStatistics();

        stage = new Stage();
        logo = new Image(new Texture("HackUCSC_Logo_AT_short.png"));
        list = new Table();

        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.down = patch;
        style.up = patch;
        style.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        style.fontColor = Color.GRAY;


        employeesButton = new TextButton("Employees", style);
        statisticsButton = new TextButton("Statistics", style);
        employerButton = new TextButton("Employer", style);

        employeesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new EmployeeScreen(core));
                dispose();
            }
        });

        statisticsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new StatisticsScreen(core));
                dispose();
            }
        });

        employerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new Password(core));
                dispose();
            }
        });

        list.align(Align.topLeft);

        list.setFillParent(true);
        list.add(logo).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 4);
        list.row();
        list.add(employeesButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() * 3 / 16);
        list.row();
        list.add(statisticsButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() * 3 / 16);
        list.row();
        list.add(employerButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() * 3 / 16);

        list.background(patch);

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
        stage.dispose();
    }
}
