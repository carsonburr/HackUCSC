package com.fourthfloor.hack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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
    private Button backArrow;

    Table list;
    TextButton findEmployeeButton;
    TextButton addEmployeeButton;
    TextButton maxsixkdayhours;
    TextButton setVD;
    TextButton changenwd;
    private Table titleBarOptions;

    public EmployerScreen(MainCore mainCore){
        PW = "1234";
        this.core = mainCore;
        stage = new Stage();
        list = new Table();
        list.align(Align.topLeft);
        backArrow = new Button(new SpriteDrawable(new Sprite(new Texture("BackArrow.png"))));
        titleBarOptions = new Table();

        list.setFillParent(true);

        titleBarOptions.add(backArrow).height(60).width(60).expand().left().pad(20);
        titleBarOptions.setBackground(new NinePatchDrawable(new NinePatch(new Texture("TitleBar.png"), 1, 1, 1, 1)));
        list.add(titleBarOptions).width(Gdx.graphics.getWidth()).height(100);
        list.row();

        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.down = patch;
        style.up = patch;
        style.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        style.fontColor = Color.GRAY;

        findEmployeeButton = new TextButton("Find Employee", style);
        addEmployeeButton = new TextButton("Add Employee", style);
        maxsixkdayhours = new TextButton("Change maximum sick day hours",style);
        setVD = new TextButton("Set number of vacation days", style);
        changenwd = new TextButton("Change the normal work hours in a week",style);


        list.add(findEmployeeButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 10);
        list.row();
        list.add(addEmployeeButton).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 10);
        list.row();
        list.add(maxsixkdayhours).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 10);
        list.row();
        list.add(setVD).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 10);
        list.row();
        list.add(changenwd).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 10);



        findEmployeeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new FindEmployeeAskScreen(core));
                dispose();

            }
        });

        changenwd.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new changenwd(core));
                dispose();

            }
        });

        setVD.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new setVD(core));
                dispose();

            }
        });

        maxsixkdayhours.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new maxSDH(core));
                dispose();

            }
        });

        backArrow.addListener(new ClickListener() {
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
        stage.dispose();
    }

}
