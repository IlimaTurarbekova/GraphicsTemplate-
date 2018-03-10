import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

    String title = "Graphics Template";
    Color background = Color.black;

    void draw(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();
        int centrX = width/2;
        int centrY = height/2;
        int minHeightWidth = Math.min(centrX, centrY);

        final int RAND_RED_MIN = 0;
        final int RAND_RED_MAX = 255;
        int alpha = 0;
        int radiusMax = minHeightWidth;
        int radiusMin = minHeightWidth/2;
        for (int i = 0; i < 500; ++i) {
            int randRadius = (int) (radiusMin + Math.random()*(radiusMax - radiusMin + 1));
            int x = (int) (centrX + Math.cos(Math.toRadians(alpha))*randRadius);
            int y = (int) (centrY + Math.sin(Math.toRadians(alpha))*randRadius);


            int randRed = (int) (RAND_RED_MIN + Math.random()*(RAND_RED_MAX - RAND_RED_MIN + 1));
            ++alpha;
            g2.setColor(new Color(randRed, 0, 0));
            g2.drawLine(centrX, centrY, x, y);

        }


    }

    public Main() {
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
        new Main();
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

