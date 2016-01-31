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
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.fourthfloor.hack.MainCore;
import com.fourthfloor.hack.utils.Database;
import com.fourthfloor.hack.utils.Statistics;

import java.awt.Label;

/**
 * Created by Thomas on 1/30/2016.
 */
public class StatisticsScreen implements Screen {

    MainCore core;

    Stage stage;
    Table list;
    public String avgSal;
    public String avgHours;
    public String totalHours;
    public String avgTotHours;
    public String totPrevHoursWorked;
    TextButton labAvgHours;
    TextButton labAvgSal;
    TextButton labAvgTotHours;
    TextButton labTotalHours;

    public StatisticsScreen(MainCore mainCore) {
        this.core = mainCore;
        stage = new Stage();
        list = new Table();

        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.down = patch;
        style.up = patch;
        style.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        style.fontColor = Color.GRAY;


        labAvgHours = new TextButton(avgHours, style);
        labAvgSal = new TextButton(avgSal, style);
        labAvgTotHours = new TextButton(avgTotHours, style);
        labTotalHours = new TextButton(totalHours, style);

        list.align(Align.topLeft);
        list.setFillParent(true);

        list.add(labAvgHours).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() / 4);
        list.row();
        list.add(labAvgSal).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() /4);
        list.row();
        list.add(labAvgTotHours).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() /4);
        list.row();
        list.add(labTotalHours).top().left().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight() /4);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(list);
    }

    boolean wtf = setStatistics();


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void show() {

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

    //Get average salary of all employees
    public boolean getAvgSal() {
        int length = Database.database.size();
        double totalSal = 0;
        for (int i = 0; i < length; i++)
            totalSal = totalSal + Database.database.get(i).getSalary();
        Statistics.avgSal = (totalSal / (length - 1));
        return true;
    }

    public boolean getTotalHours() {
        int length = Database.database.size();
        double totalHours = 0;
        for (int i = 0; i < length; i++)
            totalHours = totalHours + Database.database.get(i).getHours();

        Statistics.totalHours = (totalHours / (length - 1));
        return true;
    }

    public boolean getAvgTotHours() {
        int length = Database.database.size();
        double AvgTotHours = 0;
        for (int i = 0; i < length; i++)
            AvgTotHours = AvgTotHours + Database.database.get(i).getHours();

        Statistics.avgTotHours = (AvgTotHours / (length - 1));
        return true;
    }

    public boolean getTotPrevDaysHours() {
        int length = Database.database.size();
        double hours = 0;
        for (int i = 0; i < length; i++)
            hours = hours + Database.database.get(i).getHours();

        Statistics.totPrevHoursWorked = (hours / (length - 1));
        return true;
    }

    public boolean setLabels(){
        avgSal = "Average employee salary is: " + Statistics.avgSal;
        totalHours = "Total hours worked in business: " + Statistics.totalHours;
        avgTotHours = "Average hours worked by employee base is: " + Statistics.avgTotHours;
        totPrevHoursWorked = "Total hours worked in previous day is: ";
        return true;
    }

    public boolean setStatistics() {
        getAvgSal();
        getAvgTotHours();
        getTotalHours();
        getTotPrevDaysHours();
        setLabels();
        return true;
    }
}



