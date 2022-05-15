package com.mygame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen, InputProcessor {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;

    //GenerateHoverSprite
    Sprite[][] hoverSprites;
    Sprite[][] gameSprites;

    private Texture textureHoverCircle;
    private Pixmap pixmapHoverCircle;
    private TurnMove turnMove;
    private int stateOfGame;
    Matrix matrix;


    GameScreen()
    {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        viewport = new StretchViewport(1050, 1050, camera);
        Gdx.input.setInputProcessor(this);
        turnMove = new TurnMove();
        matrix = new Matrix();
        GenerateHoverSprites();
        GenerateGameSprites();



    }

    public void GenerateHoverSprites() {
        hoverSprites = new Sprite[15][15];
        pixmapHoverCircle = new Pixmap(70, 70, Pixmap.Format.RGBA8888);
        pixmapHoverCircle.setColor(255, 255, 255, .5f);
        pixmapHoverCircle.fillCircle(35, 35, 20);
        textureHoverCircle = new Texture(pixmapHoverCircle);
        for (int r = 0; r < 15; r++) {
            for (int c = 0; c < 15; c++) {
                hoverSprites[r][c] = new Sprite(textureHoverCircle);
                hoverSprites[r][c].setX(r * 70);
                hoverSprites[r][c].setY(c * 70);
            }
        }


    }
    public void GenerateGameSprites()
    {
        gameSprites = new Sprite[15][15];
        Pixmap pixmap = new Pixmap(70,70, Pixmap.Format.RGBA8888);
        Texture texture = new Texture(pixmap);


        for (int r = 0; r < 15; r++) {
            for (int c = 0; c < 15; c++) {

                gameSprites[r][c] = new Sprite(texture);
                gameSprites[r][c].setX(r * 70);
                gameSprites[r][c].setY(c * 70);
            }
        }
    }

    public void updateGameSprite(int xPos, int yPos, Color color)
    {

        Pixmap pixmap = new Pixmap(70,70, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillCircle(35,35,20);
        Texture texture = new Texture(pixmap);
        for (int r = 0; r < 15; r++) {
            for (int c = 0; c < 15; c++) {
                if (xPos > gameSprites[r][c].getX() && xPos < gameSprites[r][c].getX() + 70
                        && yPos > gameSprites[r][c].getY() && yPos < gameSprites[r][c].getY() + 70
                            && matrix.getMatrixIndex(r,c) == 0)
                {
                    gameSprites[r][c] = new Sprite(texture);
                    gameSprites[r][c].setX(r*70);
                    gameSprites[r][c].setY(c*70);

                    matrix.updateMatrix(r,c, turnMove.getTurn());
                    if(matrix.checkVictory())
                    {
                        Victory(turnMove.getTurn());
                    }else
                    {
                        turnMove.updateTurn();
                    }

                }
            }
        }


    }

    public void Victory(int turn)
    {
        if(turn == 0)
        {
            System.out.println( "Player1");
            stateOfGame = 1;
        }else
        {
            System.out.println( "Player2");
            stateOfGame = 2;
        }


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Input start to top-left

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(stateOfGame == 1)
        {
            Texture texture = new Texture("Player1Win.png");

            batch.draw(texture, 1050/2, 1050/2);

        }
        if(stateOfGame == 2)
        {
            Texture texture = new Texture("Player2Win.jpg");

            batch.draw(texture, 0, 0);

        }
        for(Sprite[] sprites : hoverSprites)
        {
            for(Sprite sprite : sprites)
            {
                if (Gdx.input.getX() > sprite.getX() && Gdx.input.getX() < sprite.getX() + 70
                        && 1050 - Gdx.input.getY() > sprite.getY() && 1050 - Gdx.input.getY() < sprite.getY() + 70)
                {
                    if(turnMove.getTurn() == 0)
                    {
                        sprite.setColor(1,0,0,1);
                    }
                    if(turnMove.getTurn() == 1)
                    {
                        sprite.setColor(0,1,0,1);
                    }
                    sprite.draw(batch);
                }else
                {
                    sprite.setAlpha(0);
                    sprite.draw(batch);
                }
            }
        }
        for(Sprite[] sprites : gameSprites)
        {
            for(Sprite sprite : sprites)
            {
                sprite.draw(batch);
            }
        }





        batch.end();
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(turnMove.getTurn() == 0)
        {
            updateGameSprite(Gdx.input.getX(), 1050-Gdx.input.getY(), Color.RED);
        }
        if(turnMove.getTurn() == 1)
        {
            updateGameSprite(Gdx.input.getX(), 1050-Gdx.input.getY(), Color.GREEN);
        }



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
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


}
