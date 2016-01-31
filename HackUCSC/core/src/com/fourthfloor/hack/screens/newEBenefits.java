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

/**
 * Created by Kyle on 1/30/2016.
 */
public class newEBenefits implements Screen {
    private MainCore core;
    private Stage stage;
    TextField txtInput;
    Table list;
    TextButton enterButton;
    TextButton back;
    TextButton mainmenu;
    public static String newEBenefits;

    public newEBenefits (MainCore mainCore) {
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

        TextField.TextFieldStyle style1 = new TextField.TextFieldStyle();
        style1.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        style1.fontColor = Color.GRAY;
        style1.background = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"),1,1,1,1));

        enterButton = new TextButton("Enter", style);
        back = new TextButton("Back",style);
        mainmenu = new TextButton("Main menu",style);

        txtInput = new TextField("", style1);
        txtInput.setMessageText("Type in the Employee's benefits Here");
        list.add(txtInput).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 2);
        list.row();
        list.add(enterButton);
        list.row();
        list.add(back);
        list.row();
        list.add(mainmenu);

        enterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                newEBenefits = txtInput.getText();
                core.setScreen(new newEPicture(core));
                dispose();
            }
        });

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new newESalary(core));
                dispose();
            }
        });

        mainmenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new MainMenuScreen(core));
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
