package com.fourthfloor.hack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.fourthfloor.hack.MainCore;
import com.fourthfloor.hack.utils.Database;

import java.util.ArrayList;

/**
 * Created by Carson on 1/30/2016.
 */
public class EmployeeScreen implements Screen {

    private final MainCore core;

    private Stage stage;
    private Table table;
    private Image titleBar;
    private ScrollPane scrollPane;
    private Table innerTable;
    private ArrayList<Widget> widgets;

    public EmployeeScreen(MainCore mainCore) {
        core = mainCore;

        stage = new Stage();
        table = new Table();
        titleBar = new Image(new NinePatchDrawable(new NinePatch(new Texture("TitleBar.png"), 1, 1, 1, 1)));
        innerTable = new Table();
        scrollPane = new ScrollPane(innerTable);

        table.setFillParent(true);
        table.align(Align.topLeft);
        table.add(titleBar).width(Gdx.graphics.getWidth()).height(100);
        table.row();
        table.add(scrollPane);

        for (int i = 0; i < Database.database.size(); i++) {
            widgets.add(i, new Image(Database.database.get(i).getPic()));
        }

        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);
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
