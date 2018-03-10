import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main1 extends JFrame {

    String title = "Graphics Template";
    Color background = Color.black;

    void draw(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();
        final int RADIUS_MAX = 100;
        final int RADIUS_MIN = 50;
        final int RAND_COLOR_MIN = 0;
        final int RAND_COLOR_MAX = 255;
        int randRed = 0;
        int randGreen = 0;
        int randBlue = 0;
        int alpha = 0;

        for (int k = 0; k < 35; ++k) {

            int randCentX = (int) (RADIUS_MAX + Math.random()*(width - 2*RADIUS_MAX + 1));
            int randCentY = (int) (RADIUS_MAX + Math.random()*(height - 2*RADIUS_MAX + 1));


            for (int i = 0; i < 500; ++i, ++alpha) {

                int randomRadius = (int) (RADIUS_MIN + Math.random()*(RADIUS_MAX - RADIUS_MIN + 1));

                int x = (int) (randCentX + Math.cos(Math.toRadians(alpha))*randomRadius);
                int y = (int) (randCentY + Math.sin(Math.toRadians(alpha))*randomRadius);
                int randColorNumber = (int) (RAND_COLOR_MIN + Math.random()*(RAND_COLOR_MAX - RAND_COLOR_MIN + 1));
                int randAlpha = (int) (95 + Math.random()*6);

                if (k%3 == 0) {
                    randRed = randColorNumber;
                    randGreen = 0;
                    randBlue = 0;
                } else if (k%3 == 1) {
                    randRed = 0;
                    randGreen = randColorNumber;
                    randBlue = 0;
                } else if (k%3 == 2) {
                    randRed = 0;
                    randGreen = 0;
                    randBlue = randColorNumber;
                }

                g2.setColor(new Color(randRed, randGreen, randBlue, randAlpha));
                g2.drawLine(randCentX, randCentY, x, y);


            }

        }

    }

    public Main1() {
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
