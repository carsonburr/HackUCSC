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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.fourthfloor.hack.MainCore;
import com.fourthfloor.hack.utils.Database;
import com.fourthfloor.hack.utils.Employee;

/**
 * Created by Kyle on 1/30/2016.
 */
public class maxSDH implements Screen {
    private MainCore core;
    private Stage stage;
    Table list;
    TextField txtInput;
    TextButton enter;
    private Button backArrow;
    private Table titleBarOptions;

    public maxSDH (MainCore mainCore) {
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

        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.down = patch;
        style.up = patch;
        style.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        style.fontColor = Color.GRAY;
        list.align(Align.topLeft);

        list.setFillParent(true);

        TextField.TextFieldStyle style1 = new TextField.TextFieldStyle();
        style1.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        style1.fontColor = Color.GRAY;
        style1.background = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"),1,1,1,1));

        enter = new TextButton("Enter", style);


        txtInput = new TextField("", style1);
        txtInput.setMessageText("Type in the new maximum sick day hours");
        list.add(txtInput).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 2);
        list.row();
        list.add(enter).top().left().width(Gdx.graphics.getWidth()).height(100);

        enter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String text = txtInput.getText();
                Employee.shrMax = Integer.parseInt(text);
                for (int i = 0; i < Database.database.size(); i++) {
                    Database.database.get(i).setSDH(Double.parseDouble(text));
                    Database.database.get(i).addPopup("Your maximum sick day hours have been changed to: " + Employee.shrMax + " hours.");
                }
                core.setScreen(new EmployerScreen(core));
                dispose();
            }
        });

        backArrow.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new EmployerScreen(core));
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
