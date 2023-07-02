package edu.uoc.uocoban.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameOverScreen implements Screen {

    final UocobanGame game;

    OrthographicCamera camera;

    public GameOverScreen(UocobanGame game) {
        this.game = game;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //ScreenUtils.clear(0, 0, 0.2f, 1);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.getData().setScale(2.0f);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.getGameController().restart();
            game.setScreen(new WelcomeScreen(game));
        }
        game.batch.draw(UocobanGame.assetManager.get(UocobanGame.logoAsset), 0, 0, UocobanGame.WINDOW_WIDTH, UocobanGame.WINDOW_HEIGHT);
        game.font.draw(game.batch, "Game Over!", (int) (UocobanGame.WINDOW_WIDTH * 0.3), UocobanGame.WINDOW_HEIGHT / 4);
        game.font.draw(game.batch, game.getGameController().getScore()+" points", (int) (UocobanGame.WINDOW_WIDTH * 0.3), UocobanGame.WINDOW_HEIGHT / 4 + 30);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //camera.viewportWidth = width;
        //camera.viewportHeight = height;
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
        game.batch.dispose();
    }


}
