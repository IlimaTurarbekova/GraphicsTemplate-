import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main2 extends JFrame {

    String title = "Graphics Template";
    Color background = Color.black;

    void draw(Graphics2D g2) {
        int stepX = 100;
        int y = 100;
        int bottomY = getHeight() - 100;
        int height = getHeight();
        int width = getWidth();
        int randRed = 150;

        for (int i = 0; i < 11; ++i) {

            while (randRed < 230) {
                ++randRed;
                ++stepX;
                g2.setColor(new Color(randRed, 0, 0));
                g2.drawLine(stepX, y, stepX, bottomY);
            }
            while (randRed > 150) {
                --randRed;
                ++stepX;
                g2.setColor(new Color(randRed, 0, 0));
                g2.drawLine(stepX, y, stepX, bottomY);
            }
        }

    }

    public Main2() {
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
        new Main2();
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
