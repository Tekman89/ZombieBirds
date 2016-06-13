package org.academiadecodigo.zombiebirds.utilities;

import com.badlogic.gdx.InputProcessor;
import org.academiadecodigo.zombiebirds.gameobjects.Bird;

/**
 * Created by codecadet on 13/06/16.
 */
public class InputHandler implements InputProcessor {

    private Bird myBird;

    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler(Bird bird) {
        // myBird now represents the gameWorld's bird.
        myBird = bird;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        myBird.onClick();
        return true; // Return true to say we handled the touch.
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
