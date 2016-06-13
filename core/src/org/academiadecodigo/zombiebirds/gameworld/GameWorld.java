package org.academiadecodigo.zombiebirds.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.zombiebirds.gameobjects.Bird;
import org.academiadecodigo.zombiebirds.gameobjects.ScrollHandler;
import org.academiadecodigo.zombiebirds.utilities.AssetLoader;

/**
 * Created by codecadet on 13/06/16.
 */
public class GameWorld {


    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;

    private int score = 0;




    public GameWorld(int midPointY){
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }


    public void update(float delta) {
        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.

        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
        }
    }


    public void addScore(int increment) {
        score += increment;
    }


    //Getters
    public Bird getBird() {
        return bird;

    }

    public ScrollHandler getScroller(){
        return scroller;
    }
    public int getScore() {
        return score;
    }




}
