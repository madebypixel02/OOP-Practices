package edu.uoc.uocoban.view;

import com.badlogic.gdx.InputAdapter;
import edu.uoc.uocoban.controller.Game;
import edu.uoc.uocoban.model.utils.Direction;

public class UserInputProcessor extends InputAdapter {

    Game gameController;

    public UserInputProcessor(Game gameController) {
        this.gameController = gameController;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Direction direction = Direction.getDirectionByKeyCode(keycode);
        if (direction != null)
            gameController.movePlayer(direction);
        return false;
    }

}
