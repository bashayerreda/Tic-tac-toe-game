/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame.database.model;

/**
 *
 * @author iso
 */
public class GameRecord {

    private int id;
    private int i;
    private GameMove[] gameMove;

    public GameRecord() {
        i = 0;
        gameMove = new GameMove[9];
    }

    public GameRecord(int id) {
        this.id = id;
        i = 0;
        gameMove = new GameMove[9];
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public GameMove[] getGameMove() {
        return gameMove;
    }

    public void addGameMove(GameMove gameMove) {
        if (i < 9) {
            this.gameMove[i] = gameMove;
            this.i++;
            
        }
    }

    public int getI() {
        return i;
    }

}
