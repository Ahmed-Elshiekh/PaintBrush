import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class FreeHand extends Shape {

    private ArrayList<int[]> points = new ArrayList<>();

    public FreeHand(int x1, int y1, Color col, boolean fill) {
        super(x1, y1, x1, y1, col, fill);
        points.add(new int[] { x1, y1 }); 
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getCol());
        for (int i = 1; i < points.size(); i++) {
            
            g.drawLine(points.get(i - 1)[0], points.get(i - 1)[1], points.get(i)[0], points.get(i)[1]);
        }
    }

    public void addPoint(int x, int y) {
        points.add(new int[] { x, y });
    }
}
