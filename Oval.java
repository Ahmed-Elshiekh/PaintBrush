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
public class Oval extends Shape {

    public Oval(int x1, int y1, int x2, int y2, Color col, boolean solid) {
        super(x1, y1, x2, y2, col, solid);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(getCol());
        if (isSolid()) {
            g.fillOval(Math.min(getX1(), getX2()), Math.min(getY1(),
                    getY2()), Math.abs(getX1() - getX2()), Math.abs(getY1() - getY2()));
        } else {
            g.drawOval(Math.min(getX1(), getX2()), Math.min(getY1(),
                    getY2()), Math.abs(getX1() - getX2()), Math.abs(getY1() - getY2()));
        }

    }
}
