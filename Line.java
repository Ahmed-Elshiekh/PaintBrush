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
public class Line extends Shape {

    public Line(int x1, int x2, int y1, int y2, Color col, boolean solid) {
        super(x1, x2, y1, y2, col, solid);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(getCol());

        g.drawLine(getX1(), getY1(), getX2(), getY2());

    }

}
