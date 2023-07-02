package edu.uoc.uocoban.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import edu.uoc.uocoban.controller.Game;
import edu.uoc.uocoban.model.entities.MapItem;
import edu.uoc.uocoban.model.exceptions.LevelException;

import java.io.IOException;
import java.util.Iterator;

public class GameScreen implements Screen {

    private final UocobanGame game;
    private final Game gameController;

    boolean loaded;

    public GameScreen(UocobanGame game) throws IOException, LevelException {
        this.game = game;
        gameController = game.getGameController();
        loaded = true;
        gameController.nextLevel();
        Gdx.input.setInputProcessor(new UserInputProcessor(gameController));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!gameController.isLevelDeadlocked() && !gameController.hasLostGame() && !gameController.hasLostLevel()) {

            if (loaded) {
                //gameController.update();

                Iterator<MapItem> itr = gameController.getItemMapListIterator();
                game.batch.begin();

                Color c = game.batch.getColor();

                game.font.draw(game.batch, "Level " + gameController.getCurrentLevel(), 25, 160);
                //TODO

                while (itr.hasNext()) {
                    MapItem item = itr.next();
                    game.batch.setColor(c.r, c.g, c.b, 1f);
                    game.drawImage(item.getSprite(),
                            item.getPosition().getX(),
                            item.getPosition().getY()
                    );
                }

                game.batch.setColor(c.r, c.g, c.b, 1f);
                game.batch.setColor(Color.WHITE);

                game.batch.end();

                if (gameController.isLevelCompleted()) {
                    if (gameController.isFinished())
                        game.setScreen(new GameOverScreen(game));
                    else
                        game.setScreen(new InterLevelsScreen(game));
                }
            }
        } else if (gameController.hasLostLevel() || gameController.isLevelDeadlocked()){
            try {
                gameController.decLives();
                if (gameController.getLives() > 0)
                    gameController.reloadLevel();
                else {
                    game.setScreen(new GameOverScreen(game));
                }
            } catch (LevelException e) {
                e.printStackTrace();
            }
        } else {
            game.setScreen(new GameOverScreen(game));
        }

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
        game.batch.dispose();
    }


}
