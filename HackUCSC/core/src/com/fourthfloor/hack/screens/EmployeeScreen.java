package com.fourthfloor.hack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.fourthfloor.hack.MainCore;
import com.fourthfloor.hack.utils.Constants;
import com.fourthfloor.hack.utils.Database;
import com.fourthfloor.hack.utils.Employee;
import com.fourthfloor.hack.utils.Statistics;

import java.util.ArrayList;

/**
 * Created by Carson on 1/30/2016.
 */
public class EmployeeScreen implements Screen {

    private final MainCore core;

    private Stage stage;
    private Table table;
    private Table titleBarOptions;
    private Button backArrow;
    private ScrollPane scrollPane;
    private Table innerTable;
    private ArrayList<Table> listItems;
    private ArrayList<ArrayList<Actor>> widgets;
    private int nextStat = 0;

    public EmployeeScreen(MainCore mainCore) {
        core = mainCore;

        stage = new Stage();
        table = new Table();
        backArrow = new Button(new SpriteDrawable(new Sprite(new Texture("BackArrow.png"))));
        titleBarOptions = new Table();
        innerTable = new Table();
        scrollPane = new ScrollPane(innerTable);
        listItems = new ArrayList<Table>();
        widgets = new ArrayList<ArrayList<Actor>>();

        titleBarOptions.add(backArrow).height(60).width(60).expand().left().pad(20);
        titleBarOptions.setBackground(new NinePatchDrawable(new NinePatch(new Texture("TitleBar.png"), 1, 1, 1, 1)));

        table.setFillParent(true);
        table.align(Align.topLeft);
        table.add(titleBarOptions).width(Gdx.graphics.getWidth()).height(100);
        table.row();
        table.add(scrollPane).fillX();


        NinePatchDrawable patch = new NinePatchDrawable(new NinePatch(new Texture("ListItem.png"), 1, 1, 1, 1));
        table.background(patch);
        Texture empty = new Texture("Empty.png");

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("Arial_small.fnt"), Gdx.files.internal("Arial_small_0.png"), false);
        labelStyle.fontColor = Color.DARK_GRAY;

        Label.LabelStyle labelBigStyle = new Label.LabelStyle();
        labelBigStyle.font = new BitmapFont(Gdx.files.internal("Arial3.fnt"), Gdx.files.internal("Arial3_0.png"), false);
        labelBigStyle.fontColor = Color.DARK_GRAY;

        for (int i = 0; i < Database.database.size(); i++) {
            listItems.add(i, new Table());
            widgets.add(i, new ArrayList<Actor>());

            int currWidget = 0;
            widgets.get(i).add(currWidget, new Image(Database.database.get(i).getPic()));
            listItems.get(i).add(widgets.get(i).get(currWidget)).align(Align.topLeft).width(Constants.PROFILE_PIC_WIDTH).height(Constants.PROFILE_PIC_HEIGHT).pad(10).left();

            currWidget++;
            widgets.get(i).add(currWidget, new Label(Database.database.get(i).getName(), labelBigStyle));
            listItems.get(i).add(widgets.get(i).get(currWidget)).height(Constants.PROFILE_PIC_HEIGHT).pad(10).left();

            currWidget++;
            widgets.get(i).add(currWidget, new Label("", labelStyle));
            listItems.get(i).add(widgets.get(i).get(currWidget)).height(Constants.PROFILE_PIC_HEIGHT).pad(10).right().expandX();

            currWidget++;
            widgets.get(i).add(currWidget, new Button(
                    new SpriteDrawable(new Sprite(new Texture("ClockUnchecked.png"))),
                    new SpriteDrawable(new Sprite(new Texture("ClockPressed.png"))),
                    new SpriteDrawable(new Sprite(new Texture("ClockChecked.png")))));
            listItems.get(i).add(widgets.get(i).get(currWidget)).width(Constants.PROFILE_PIC_WIDTH).height(Constants.PROFILE_PIC_HEIGHT).pad(10);
            final int j = i;
            final int curr = currWidget;
            widgets.get(i).get(currWidget).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Database.database.get(j).clock();

                    int numStats = 2;

                    if (!Database.database.get(j).popup.isEmpty()) {
                        changeText(j, curr-1, Database.database.get(j).popup.get(0));

                        Database.database.get(j).popup.remove(0);
                        for (int i = 1; i < Database.database.get(j).popup.size(); i++) {
                            Database.database.get(j).popup.set(i-1, Database.database.get(j).popup.get(i));
                            if (i+1 == Database.database.get(j).popup.size()) {
                                Database.database.get(j).popup.remove(i);
                            }
                        }
                    } else if (nextStat == 0) {
                        changeText(j, curr-1, "Your salary is "+Math.round(100 * Database.database.get(j).getSalary() / Statistics.avgSal)+"% \nof the average salary");
                        nextStat++;
                        nextStat %= numStats;
                    } else if (nextStat == 1) {
                        changeText(j, curr-1, "Your total hours are "+Math.round(100*Database.database.get(j).getHours()/Statistics.avgTotHours)+"% \nof the average employee's total hours");
                        nextStat++;
                        nextStat %= numStats;
                    } else {
                        changeText(j, curr-1, "No text to show");
                    }

                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            changeText(j, curr-1, "");
                        }
                    }, 3.0f);
                }
            });

            listItems.get(i).setBackground(patch);

            innerTable.add(listItems.get(i)).expandX().align(Align.left).fillX();
            innerTable.row();
        }

        backArrow.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                core.setScreen(new MainMenuScreen(core));
                dispose();
            }
        });

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        //Gdx.app.log("Inner Table", "("+listItems.get(0).getX()+", "+listItems.get(0).getY()+") ("+listItems.get(0).getWidth()+", "+listItems.get(0).getHeight()+")");
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    private void changeText(int x, int y, String text) {
        if (widgets.get(x).get(y) instanceof Label) {
            ((Label) widgets.get(x).get(y)).setText(text);
        }
    }
}
