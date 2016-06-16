package org.academiadecodigo.zombiebirds.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.zombiebirds.gameobjects.Bird;
import org.academiadecodigo.zombiebirds.gameobjects.ScrollHandler;
import org.academiadecodigo.zombiebirds.utilities.AssetLoader;
import org.academiadecodigo.zombiebirds.utilities.GameState;

/**
 * Created by codecadet on 13/06/16.
 */
public class GameWorld {

    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;

    private int score = 0;

    private int midPointY;

    private GameState currentState;

    private boolean cheated;

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midPointY = midPointY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }


    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                cheated = false;
                break;

            case RUNNING:
                updateRunning(delta);
                break;

            case CHEAT:
                updateRunning(delta);
                cheated = true;
                break;

            default:
                break;
        }

    }



    private void updateReady(float delta) {
        // Do nothing for now
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive() && currentState != GameState.CHEAT) {
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore() && !hasCheated()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }


    public void addScore(int increment) {
        score += increment;
    }

    public void cheatMode() {

        if(currentState == GameState.RUNNING) {
            currentState = GameState.CHEAT;
        } else if (currentState == GameState.CHEAT){
            currentState = GameState.RUNNING;
        }

    }


    //Getters
    public Bird getBird() {
        return bird;

    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }


    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isCheat(){
        return currentState == GameState.CHEAT;
    }

    public boolean hasCheated(){
        return cheated;
    }

}
