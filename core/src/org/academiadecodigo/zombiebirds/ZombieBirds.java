package org.academiadecodigo.zombiebirds;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import org.academiadecodigo.zombiebirds.gamescreen.GameScreen;
import org.academiadecodigo.zombiebirds.utilities.AssetLoader;

public class ZombieBirds extends Game{

    @Override
    public void create() {
        Gdx.app.log("ZBGame", "created");
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
