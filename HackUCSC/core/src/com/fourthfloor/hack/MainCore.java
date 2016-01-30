package com.fourthfloor.hack;

import com.badlogic.gdx.Game;
import com.fourthfloor.hack.screens.SplashScreen;

public class MainCore extends Game {

	@Override
	public void create () {
		setScreen(new SplashScreen());
	}
}
