
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Eraser extends Shape {

    private ArrayList<int[]> points = new ArrayList<>();

    public Eraser(int x1, int y1, Color col) {
        super(x1, y1, x1, y1, col, false); 
        points.add(new int[] { x1, y1 });
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE); 
        for (int[] point : points) {
            g.fillRect(point[0] - 5, point[1] - 5, 10, 10); 
			
        }
		
    }

    public void addPoint(int x, int y) {
        points.add(new int[] { x, y });
    }
}
