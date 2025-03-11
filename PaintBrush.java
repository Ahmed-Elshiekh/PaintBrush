/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Image;



/**
 *
 * @author MG
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PaintBrush extends Applet {

    private Image offscreenImage; 
    private Graphics offscreenGraphics;
    private Checkbox fill;
    private Label label;
    private String currentDrawing;
    private Color currentColor;
    private Button oval, space, rect,undo, line, freeHand, eraser, red, green, blue, clear;
    private int startX, startY, endX, endY;
    private boolean isDragged = false;
    private boolean checkFill;
    private ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    public void init() {
        offscreenImage = createImage(getWidth(), getHeight());
        offscreenGraphics = offscreenImage.getGraphics();
        offscreenGraphics.setColor(getBackground());
        offscreenGraphics.fillRect(0, 0, getWidth(), getHeight());
        currentColor = Color.BLACK;
        undo = new Button("Undo");
        red = new Button("Red");
        green = new Button("Green");
        blue = new Button("Blue");
        clear = new Button("Clear");

        space = new Button("");
        oval = new Button("Oval");
        rect = new Button("Rect");
        line = new Button("Line");
        freeHand = new Button("Freehand");
        eraser = new Button("Eraser");
        fill = new Checkbox("Fill");

        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        fill.setFont(largeFont);
        add(fill);

        fill.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkFill = fill.getState();
            }
        });

        red.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.RED;
            }
        });
        add(red);

        green.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.GREEN;
            }
        });
        add(green);

        blue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentColor = Color.BLUE;
            }
        });
        add(blue);

        add(space);

        oval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentDrawing = "oval";
            }
        });
        add(oval);

        rect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentDrawing = "rect";
            }
        });
        add(rect);

        line.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentDrawing = "line";
            }
        });
        add(line);

        freeHand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentDrawing = "freehand";
            }
        });
        add(freeHand);

          eraser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  // الممحاة ستكون باللون الأبيض
                currentDrawing = "eraser";   // تعيين الوضع إلى "eraser"
            }
        });

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                offscreenGraphics.setColor(getBackground());
                offscreenGraphics.fillRect(0, 0, getWidth(), getHeight());
                repaint();
            }
        });
		undo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        if (shapes.size() > 0) {
            shapes.remove(shapes.size() - 1); 
            offscreenGraphics.setColor(getBackground());
             offscreenGraphics.fillRect(0, 0, getWidth(), getHeight()); 
            
            for (Shape shape : shapes) {
                shape.draw(offscreenGraphics);
            }
            repaint();
        }
    }
});
        add(eraser);
        add(clear);
		add(undo);

        // mouse pressed
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                switch (currentDrawing) {
                    case "oval":
                        startX = e.getX();
                        startY = e.getY();
                        isDragged = false;
                        shapes.add(new Oval(startX, startY, startX, startY, currentColor, checkFill));
                        break;
                    case "rect":
                        startX = e.getX();
                        startY = e.getY();
                        isDragged = false;
                        shapes.add(new Rect(startX, startY, startX, startY, currentColor, checkFill));
                        break;
                    case "line":
                        startX = e.getX();
                        startY = e.getY();
                        isDragged = false;
                        shapes.add(new Line(startX, startY, startX, startY, currentColor, checkFill));
                        break;
                    case "freehand":
                        startX = e.getX();
                        startY = e.getY();
                        isDragged = false;
                        shapes.add(new FreeHand(startX, startY, currentColor, checkFill));
                        break;
                    case "eraser":
                        startX = e.getX();
                        startY = e.getY();
						isDragged = false;
						shapes.add(new Eraser(startX, startY, currentColor));
                        break;
                }
            }
        });

        // mouse dragged
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                isDragged = true;
                endX = e.getX();
                endY = e.getY();

                if (currentDrawing.equals("freehand")) {
                    FreeHand freehand = (FreeHand) shapes.get(shapes.size() - 1);
                    freehand.addPoint(endX, endY);
                } if (currentDrawing.equals("eraser")) {
                    
                    Eraser eraser = (Eraser) shapes.get(shapes.size() - 1);
                    eraser.addPoint(endX, endY); 
					 eraser.draw(offscreenGraphics);
                }
			else {
                    shapes.get(shapes.size() - 1).setX2(endX);
                    shapes.get(shapes.size() - 1).setY2(endY);
                }

                offscreenGraphics.setColor(getBackground());
                offscreenGraphics.fillRect(0, 0, getWidth(), getHeight());
                for (Shape p : shapes) {
                    p.draw(offscreenGraphics);
                }

                repaint();
            }
        });

        // mouse released
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                switch (currentDrawing) {
                    case "oval":
                    case "rect":
                    case "line":
                        if (!isDragged) {
                            shapes.remove(shapes.size() - 1);
                        }
                        repaint();
                        break;
                }
            }
        });
    }

    public void paint(Graphics g) {
        g.drawImage(offscreenImage, 0, 0, this);
    }
}
