import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Visualizer extends JPanel{

    private BufferedImage image;
    private Graphics2D graphics2D;

    private int frameWidth =  600;
    private int frameHeigth = 600;

    private int maxX;
    private int maxY;
    
    private Point[] points;
    
    private JFrame frame;

    final static private int DIAMETR = 15;

    private int xLostPixels;
    private int yLostPixels;

    public Visualizer(int maxX,int maxY){

        this.maxX = maxX + 2;
        this.maxY = maxY + 2;

        setSize(new Dimension(frameWidth, frameHeigth));

        xLostPixels = (int)((((double)frameWidth/this.maxX) - (frameWidth/this.maxX)) * this.maxX);
        yLostPixels = (int)((((double) frameHeigth/this.maxY) - (frameHeigth/this.maxY)) * this.maxY);

        frame = new JFrame();
        frame.setSize(frameWidth,frameHeigth + 30);
        frame.setContentPane(this);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setPoints(Point[] points)
    {
        if(points != null)
        {
            Point array[] = new Point[points.length];

            for (int i = 0; i < array.length; i++)
            {
                array[i] = new Point(points[i].x * (frameWidth/maxX) + frameWidth/maxX,
                		-points[i].y * (frameHeigth / maxY) + frameHeigth - frameHeigth/maxY - yLostPixels);
            }

            this.points = array;
        }
    }

    public void display() {

        image = new BufferedImage(frameWidth, frameHeigth, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) image.getGraphics();

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, frameWidth, frameHeigth);

        drawCell(graphics2D);

        if(points != null && points.length > 0)
        {
            int x = points[0].x, y = points[0].y;

            graphics2D.setColor(Color.BLUE);

            if(points.length > 2 )
            {
                graphics2D.drawLine(x, y, points[points.length-1].x, points[points.length-1].y);
            }

            for (int i = 0; i < points.length; i++) {
                graphics2D.drawLine(x, y, points[i].x, points[i].y);
                graphics2D.fillOval(points[i].x - DIAMETR / 2, points[i].y - DIAMETR / 2, DIAMETR, DIAMETR);
                x = points[i].x;
                y = points[i].y;
            }
        }
        
        getGraphics().drawImage(image, 0, 0, null);
    }

    private void drawCell(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.BLACK);

        graphics2D.setFont(new Font("Tahoma", 0, 12));

        int xCellSize = frameWidth / maxX;
        int yCellSize = frameHeigth / maxY;
        
        int xIndent = frameWidth - xCellSize - xLostPixels;
        int yIndent = frameHeigth - yCellSize - yLostPixels;
        
        int lineXCoordinate = xCellSize;
        for(int i = 0; i < maxX - 1; i++)
        {
            graphics2D.drawLine(lineXCoordinate,yCellSize,lineXCoordinate, yIndent);
            graphics2D.drawString(i + "", lineXCoordinate - 5, yIndent + 15);
            lineXCoordinate += xCellSize;
        }

        int lineYCoordinate = yCellSize;
        for(int i = 0; i < maxY - 1; i++)
        {
            graphics2D.drawLine(xCellSize, lineYCoordinate, xIndent, lineYCoordinate);
            graphics2D.drawString(maxY - i - 2 + "", xCellSize - 20, lineYCoordinate + 5);
            lineYCoordinate += yCellSize;
        }
    }
}
