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
public class GameMove {

    private int cellNumber;
    private int cellOrder;
    private char cellType;

    public GameMove(int cellNumber, int cellOrder, char cellType) {

        this.cellNumber = cellNumber;
        this.cellOrder = cellOrder;
        this.cellType = cellType;
    }

    public char getCellType() {
        return cellType;
    }

    public void setCellType(char cellType) {
        this.cellType = cellType;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getCellOrder() {
        return cellOrder;
    }

    public void setCellOrder(int cellOrder) {
        this.cellOrder = cellOrder;
    }

}
