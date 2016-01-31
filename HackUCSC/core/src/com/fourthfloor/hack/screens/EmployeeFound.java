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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
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
    TextButton changeSalary;
    Image pic;
    private Button backArrow;
    private Table titleBarOptions;


    public EmployeeFound (MainCore mainCore) {
        stage = new Stage();
        this.core = mainCore;
        list = new Table();
        titleBarOptions = new Table();

        backArrow = new Button(new SpriteDrawable(new Sprite(new Texture("BackArrow.png"))));
        titleBarOptions = new Table();

        titleBarOptions.add(backArrow).height(60).width(60).expand().left().pad(20);
        titleBarOptions.setBackground(new NinePatchDrawable(new NinePatch(new Texture("TitleBar.png"), 1, 1, 1, 1)));
        list.add(titleBarOptions).width(Gdx.graphics.getWidth()).height(100);
        list.row();
        Gdx.input.setInputProcessor(stage);

        for(int i = 0;i<Database.database.size();i++){
            if(FindEmployeeAskScreen.EmployeeName.equals(Database.database.get(i).getName())){
                pic = new Image(Database.database.get(i).getPic());
            }
        }

        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.down = patch;
        style.up = patch;
        style.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        style.fontColor = Color.GRAY;
        list.align(Align.topLeft);
        list.setFillParent(true);

        for(int i = 0; i< Database.database.size();i++){
            if(FindEmployeeAskScreen.EmployeeName.equals(Database.database.get(i).getName())){
                EF = new TextButton("" + Database.database.get(i).toString(), style);
            }
        }


        changeSalary = new TextButton("Change salary", style);

        list.add(pic).width(96).height(96).align(Align.top);
        list.row();
        list.add(EF).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 2);
        list.row();
        list.add(changeSalary).top().left().width(Gdx.graphics.getWidth()).height(100);

        list.background(patch);


        backArrow.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new FindEmployeeAskScreen(core));
                dispose();
            }
        });

        changeSalary.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new ChangeSalary(core));
                dispose();

            }
        });


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
