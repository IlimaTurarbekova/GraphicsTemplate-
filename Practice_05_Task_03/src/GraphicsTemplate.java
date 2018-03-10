import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GraphicsTemplate extends JFrame {

    String title = "Graphics Template";
    Color background = Color.BLACK;

    void draw(Graphics2D g2) {
        final int RAY_COUNT = 8;
        final int RADIUS = 150;
        final Color COLOR = Color.RED;

        int width = getWidth();
        int height = getHeight();

        int cx = width / 2;
        int cy = height / 2;


        drawStar(g2, cx, cy, RADIUS + 50, RADIUS * 0.4, RAY_COUNT, COLOR);
        drawStar(g2, cx + 230, cy + 230, RADIUS, RADIUS * 0.4, RAY_COUNT, Color.YELLOW);
        drawStar(g2, 460, 460, RADIUS, RADIUS * 0.4, RAY_COUNT, Color.YELLOW);
        drawStar(g2, cx - 230, cy - 230, RADIUS, RADIUS * 0.4, RAY_COUNT, Color.YELLOW);

    }

    void drawStar(
            Graphics2D g2,
            int x, int y,
            double outerRadius, double innerRadius,
            int rayCount,
            Color color
    ) {
        final double FULL_ANGLE = 2.0 * Math.PI;

        double alpha = 0;
        double alphaStep = FULL_ANGLE / rayCount;

        g2.setColor(color);

        double radius = (rayCount - 1) % 2 == 0 ? outerRadius : innerRadius;
        int previusEndX = (int) (x + Math.cos(alpha - alphaStep) * radius);
        int previusEndY = (int) (y + Math.sin(alpha - alphaStep) * radius);
        for (int ray = 0; ray < rayCount; ++ray, alpha += alphaStep) {
            radius = ray % 2 == 0 ? outerRadius : innerRadius;
            int endX = (int) (x + Math.cos(alpha) * radius);
            int endY = (int) (y + Math.sin(alpha) * radius);

            g2.drawLine(x, y, endX, endY);
            g2.drawLine(endX, endY, previusEndX, previusEndY);

            previusEndX = endX;
            previusEndY = endY;
        }
    }

    public GraphicsTemplate() {
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        DrawPanel panel = new DrawPanel();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.exit(0);
            }
        });
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicsTemplate();
    }

    class DrawPanel extends JPanel {
        public DrawPanel() {
            setBackground(background);
            setFocusable(true);
            requestFocusInWindow();
            setDoubleBuffered(true);
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2.setRenderingHints(hints);

            super.paintComponent(g);
            draw(g2);
        }
    }

}
