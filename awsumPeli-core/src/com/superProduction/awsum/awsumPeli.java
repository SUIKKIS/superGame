package com.superProduction.awsum;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class awsumPeli extends ApplicationAdapter {

	public static final String VERSION = "0.0.0.2 Pre-Alpha";

	// Textures
	Texture texture_player;
	Texture laser;
	Texture backGround;

	// Random lasers
	Array<Rectangle> lasers;
	long spawnTime;

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
		laser = new Texture("laser.png");

		// Load sound effect and background music
		hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.mp3"));
		muzak = Gdx.audio.newMusic(Gdx.files.internal("dream.mp3"));

		// set music to loop and set it to play immediately
		muzak.setLooping(true);
		muzak.play();
		muzak.setVolume((float) 0.2);

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

		// Lasers
		lasers = new Array<Rectangle>();
		spawnLaser();

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
		
		for(Rectangle laserReal : this.lasers) {
			
			batch.draw(laser, laserReal.x, laserReal.y);
			
		}
		
		batch.end();
		
		// Laser spawn and garbage collection
		if (TimeUtils.nanoTime() - spawnTime > 100000000)
			spawnLaser();

		Iterator<Rectangle> iter = lasers.iterator();
		while (iter.hasNext()) {

			Rectangle laser = iter.next();
			laser.y -= 200 * Gdx.graphics.getDeltaTime();
			if (laser.y + 37 < 0)
				iter.remove();
			if(laser.overlaps(player)) {
				
				hitSound.play();
				iter.remove();
				
			}
		}
		
		// Touch

		if (Gdx.input.isTouched()) {

			Vector3 osoitin = new Vector3();
			osoitin.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(osoitin);
			player.x = osoitin.x - 64 / 2;
			player.y = osoitin.y - 64 / 2;
		}

		// Checking if player uses keyboard

		if (Gdx.input.isKeyPressed(Keys.LEFT))
			player.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			player.x += 200 * Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Keys.UP))
			player.y += 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			player.y -= 200 * Gdx.graphics.getDeltaTime();

		// Checking boundaries

		if (player.x < 0)
			player.x = 0;
		if (player.x > 736)
			player.x = 736;

		if (player.y < 0)
			player.y = 0;
		if (player.y > 336)
			player.y = 336;

	}

	private void spawnLaser() {

		Rectangle laser = new Rectangle();
		laser.x = MathUtils.random(0, 800 - 37);
		laser.y = 480;
		laser.width = 13;
		laser.height = 37;
		this.lasers.add(laser);
		spawnTime = TimeUtils.nanoTime();

	}

}
