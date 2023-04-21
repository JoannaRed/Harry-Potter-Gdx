package com.potter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator;
import java.util.Vector;

public class Drop extends ApplicationAdapter {

	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Rectangle bucket;
	private Array<Rectangle> raindrops;
	private long lastDropTime;

	@Override
	public void create () {
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		rainMusic.setLooping(true);
		rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, GUIParams.WIDTH, GUIParams.HEIGHT);

		batch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.x = GUIParams.WIDTH / 2 - GUIParams.ENTITY_SIZE / 2;
		bucket.y = 20;
		bucket.width = GUIParams.ENTITY_SIZE;
		bucket.height = GUIParams.ENTITY_SIZE;

		raindrops = new Array<>();
		spawnRaindrop();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		for(Rectangle raindrop : raindrops){
			batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		batch.end();

		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = (int) (touchPos.x - GUIParams.ENTITY_SIZE / 2);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		}

		if(bucket.x < 0){
			bucket.x = 0;
		}
		if(bucket.x > GUIParams.WIDTH - GUIParams.ENTITY_SIZE){
			bucket.x = GUIParams.WIDTH - GUIParams.ENTITY_SIZE;
		}

		if(TimeUtils.nanoTime() - lastDropTime > 1_000_000_000){
			spawnRaindrop();
		}

		for(Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ){
			Rectangle raindrop = iter.next();
			raindrop.y -=200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + GUIParams.ENTITY_SIZE < 0) {
				iter.remove();

			}
			if(raindrop.overlaps(bucket)){
				dropSound.play();
				iter.remove();
			}
		}

	}

	private void spawnRaindrop(){
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, GUIParams.WIDTH - GUIParams.ENTITY_SIZE);
		raindrop.y = GUIParams.HEIGHT;
		raindrop.width = GUIParams.ENTITY_SIZE;
		raindrop.height = GUIParams.ENTITY_SIZE;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void dispose () {
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}
}
