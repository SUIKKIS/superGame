package com.superProduction.awsum;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class awsumPeli extends ApplicationAdapter {

	public static final String VERSION = "0.0.0.2 Pre-Alpha";
	// Textures
	Texture texture_player;
	Texture fireBall;
	Texture backGround;
	// Sounds
	Sound hitSound;
	Music muzak;
	// Camera
	OrthographicCamera camera;
	// 2D Render
	SpriteBatch batch;
	// Player logic
	Rectangle player;

	@Override
	public void create() {

		// Load images textures for player, background and flying fireballs
		backGround = new Texture("background.png");
		texture_player = new Texture("player1.png");
		fireBall = new Texture("laser.png");

		// Load sound effect and background music
		hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.mp3"));
		muzak = Gdx.audio.newMusic(Gdx.files.internal("dream.mp3"));

		// set music to loop and set it to play immediately
		muzak.setLooping(true);
		muzak.play();

		// Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);

		// 2D render
		batch = new SpriteBatch();

		// Player
		player = new Rectangle();
		player.x = 800 / 2 - 64 / 2;
		player.y = 200;
		player.width = 64;
		player.height = 64;

	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Updating camera position
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(texture_player, player.x, player.y);
		batch.end();

		// Touch

		if (Gdx.input.isTouched()) {

			Vector3 osoitin = new Vector3();
			osoitin.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(osoitin);
			player.x = osoitin.x - 64 / 2;
			player.y = osoitin.y - 64 / 2;

		}

	}
}
