package edu.uoc.uocoban.view;

//https://libgdx.com/wiki/app/starter-classes-and-configuration

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.uoc.uocoban.model.utils.Sprite;

import java.io.IOException;

public class UocobanGame extends Game {

    public static final int WINDOW_WIDTH = 915;
    public static final int WINDOW_HEIGHT = 768;

    SpriteBatch batch;

    edu.uoc.uocoban.controller.Game gameController;

    public static AssetDescriptor<Texture> wallAsset, pathAsset, playerAsset,
                            bigCrateAsset, bigCrateDestinationAsset, bigCrateOnDestinationAsset,
                            smallCrateAsset, smallCrateDestinationAsset, smallCrateOnDestinationAssset, logoAsset;

    public static AssetManager assetManager;

    BitmapFont font;

    public static final int CELL_SIZE = 60;

    @Override
    public void create(){
        try {
            gameController = new edu.uoc.uocoban.controller.Game("levels/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        font = new BitmapFont();
        loadImages();
        assetManager.finishLoading();
        this.setScreen(new WelcomeScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    public void loadImages() {

        wallAsset = new AssetDescriptor<>(Sprite.WALL.getImageSrc(), Texture.class);
        pathAsset = new AssetDescriptor<>(Sprite.PATH.getImageSrc(), Texture.class);
        playerAsset = new AssetDescriptor<>(Sprite.PLAYER.getImageSrc(), Texture.class);

        bigCrateAsset = new AssetDescriptor<>(Sprite.BIG_BOX.getImageSrc(), Texture.class);
        bigCrateDestinationAsset = new AssetDescriptor<>(Sprite.BIG_BOX_DESTINATION.getImageSrc(), Texture.class);
        bigCrateOnDestinationAsset = new AssetDescriptor<>(Sprite.BIG_BOX_ON_DESTINATION.getImageSrc(), Texture.class);

        smallCrateAsset = new AssetDescriptor<>(Sprite.SMALL_BOX.getImageSrc(), Texture.class);
        smallCrateDestinationAsset = new AssetDescriptor<>(Sprite.SMALL_BOX_DESTINATION.getImageSrc(), Texture.class);
        smallCrateOnDestinationAssset = new AssetDescriptor<>(Sprite.SMALL_BOX_ON_DESTINATION.getImageSrc(), Texture.class);

        logoAsset = new AssetDescriptor<>("images/logo.png", Texture.class);

        assetManager.load(wallAsset);
        assetManager.load(pathAsset);
        assetManager.load(playerAsset);

        assetManager.load(bigCrateAsset);
        assetManager.load(bigCrateDestinationAsset);
        assetManager.load(bigCrateOnDestinationAsset);

        assetManager.load(smallCrateAsset);
        assetManager.load(smallCrateDestinationAsset);
        assetManager.load(smallCrateOnDestinationAssset);

        assetManager.load(logoAsset);

    }

    AssetDescriptor<Texture> getAssertBySprite(Sprite sprite) {
        return switch (sprite) {
            case WALL -> wallAsset;
            case PATH -> pathAsset;
            case PLAYER -> playerAsset;
            case BIG_BOX -> bigCrateAsset;
            case BIG_BOX_DESTINATION -> bigCrateDestinationAsset;
            case BIG_BOX_ON_DESTINATION -> bigCrateOnDestinationAsset;
            case SMALL_BOX -> smallCrateAsset;
            case SMALL_BOX_DESTINATION -> smallCrateDestinationAsset;
            case SMALL_BOX_ON_DESTINATION -> smallCrateOnDestinationAssset;
        };
    }

    void drawImage(Sprite sprite, int x, int y) {
        batch.draw(UocobanGame.assetManager.get(getAssertBySprite(sprite)),
                x * UocobanGame.CELL_SIZE,
                (WINDOW_HEIGHT - (y * UocobanGame.CELL_SIZE) - UocobanGame.CELL_SIZE),
                CELL_SIZE, CELL_SIZE);
    }

    public edu.uoc.uocoban.controller.Game getGameController(){
        return this.gameController;
    }
}
