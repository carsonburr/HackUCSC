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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.fourthfloor.hack.MainCore;
import com.fourthfloor.hack.utils.Database;

/**
 * Created by Kyle on 1/30/2016.
 */
public class EmployeeFound implements Screen {
    private MainCore core;
    private Stage stage;
    Table list;
    TextButton EF;
    TextButton back;

    public EmployeeFound (MainCore mainCore) {
        stage = new Stage();
        this.core = mainCore;
        list = new Table();

        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.down = patch;
        style.up = patch;
        style.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        style.fontColor = Color.GRAY;

        list.setFillParent(true);

        for(int i = 0; i< Database.database.size();i++){
            if(FindEmployeeAskScreen.EmployeeName.equals(Database.database.get(i).getName())){
                EF = new TextButton("" + Database.database.get(i).toString(), style);
            }
        }

        back = new TextButton("Back", style);

        list.add(EF).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 2);
        list.row();
        list.add(back).top().left().width(Gdx.graphics.getWidth()).height(100);
        list.row();

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                core.setScreen(new FindEmployeeAskScreen(core));
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