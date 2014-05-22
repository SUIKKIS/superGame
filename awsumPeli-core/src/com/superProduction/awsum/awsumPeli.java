package com.superProduction.awsum;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class awsumPeli extends ApplicationAdapter {

	public static final String VERSION = "0.0.0.0 Pre-Alpha";

	Texture player;
	Texture fireBall;

	Sound hitSound;
	Music muzak;

	@Override
	public void create() {

		// Load images textures for player and flying fireballs
		player = new Texture("asd.jpg");
		fireBall = new Texture("fireBall.jpg");

		// Load sound effect and background music
		hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.mp3"));
		muzak = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));

		// set music to loop and set it to play immediately
		muzak.setLooping(true);
		muzak.play();

	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}
}
