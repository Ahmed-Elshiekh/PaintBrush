/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author MG
 */
public abstract class Shape {

    private int x1, x2, y1, y2;
    Color col;
    private boolean solid;

    public Shape(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public Shape(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public Shape(int x1, int y1, int x2, int y2, Color col, boolean solid) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.col = col;
        this.solid = solid;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public Color getCol() {
        return col;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setCol(Color col) {
        this.col = col;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    abstract void draw(Graphics g);

}
