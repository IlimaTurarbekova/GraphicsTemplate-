import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GraphicsTemplate extends JFrame {

    String title = "Graphics Template";
    Color background = Color.BLACK;
    final int RED = 255;
    final int GREEN = 255;
    final int BLUE = 255;
    int width = getWidth();
    int height = getHeight();

    int radius = 25;
    int diameter = radius * 2;

    int centerX = width / 2 - radius;
    int centerY = height / 2 - radius;
    int counter = 50;
    int max = 10;
    int min = 1;
    void draw(Graphics2D g2) {
        for (int i = 0; i < counter; i++) {
            int size = (int) (1 + Math.random() * (max - min + 1));
            centerX = (int)(-size + Math.random() * (getWidth() + size + 1));
            centerY = (int)(-size + Math.random() * (getHeight() + size + 1));
            int red = (int)(Math.random() * (RED + 1));
            int green = (int)(Math.random() * (GREEN + 1));
            int blue = (int)(Math.random() * (BLUE + 1));
            Color color = new Color(red, green, blue);

            g2.setColor(color);
            g2.fillOval(centerX, centerY, diameter, diameter);
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
