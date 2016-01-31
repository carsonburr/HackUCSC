package com.fourthfloor.hack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.fourthfloor.hack.MainCore;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Kyle on 1/30/2016.
 */
public class EmployerScreen implements Screen {
    private String PW;
    private MainCore core;
    private Stage stage;

    Table list;
    TextButton findEmployeeButton;
    TextButton addEmployeeButton;
    TextButton back;

    public EmployerScreen(MainCore mainCore){
        PW = "1234";
        this.core = mainCore;
        stage = new Stage();
        list = new Table();
        list.align(Align.topLeft);

        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.down = patch;
        style.up = patch;
        style.font = new BitmapFont(Gdx.files.internal("Arial_small.fnt"), Gdx.files.internal("Arial_small_0.png"), false);
        style.fontColor = Color.GRAY;

        findEmployeeButton = new TextButton("Find Employee", style);
        addEmployeeButton = new TextButton("Add Employee", style);
        back =  new TextButton("Back to main menu", style);

        list.setFillParent(true);
        list.add(findEmployeeButton).top().left().width(Gdx.graphics.getWidth() / 6).height(Gdx.graphics.getHeight() / 10);
        list.row();
        list.add(addEmployeeButton).top().left().width(Gdx.graphics.getWidth() / 6).height(Gdx.graphics.getHeight() / 10);
        list.row();
        list.add(back).top().left().width(Gdx.graphics.getWidth() / 6).height(Gdx.graphics.getHeight() / 10);



        findEmployeeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new FindEmployeeAskScreen(core));
                dispose();

            }
        });

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new MainMenuScreen(core));
                dispose();

            }
        });

        addEmployeeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new newEName(core));
                dispose();

            }
        });

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
