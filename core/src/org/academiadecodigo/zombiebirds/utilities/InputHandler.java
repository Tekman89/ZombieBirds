package org.academiadecodigo.zombiebirds.utilities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import org.academiadecodigo.zombiebirds.gameobjects.Bird;
import org.academiadecodigo.zombiebirds.gameworld.GameWorld;

/**
 * Created by codecadet on 13/06/16.
 */
public class InputHandler implements InputProcessor {

    private Bird myBird;
    private GameWorld myWorld;

    public InputHandler(GameWorld myWorld) {
        // myBird now represents the gameWorld's bird.
        this.myWorld = myWorld;
        myBird = myWorld.getBird();
    }

    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler(Bird bird) {
        // myBird now represents the gameWorld's bird.
        myBird = bird;
    }


    public boolean click(){

        if (myWorld.isReady()) {
            myWorld.start();
        }

        myBird.onClick();

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            // Reset all variables, go to GameState.READ
            myWorld.restart();
        }

        return true;

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return click();
    }


    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){

            case Input.Keys.C:
                myWorld.cheatMode();
                return true;
            case Input.Keys.SPACE:
                return click();
            default:
                return false;

        }

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
