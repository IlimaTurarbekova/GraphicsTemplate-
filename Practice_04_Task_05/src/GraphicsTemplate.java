import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GraphicsTemplate extends JFrame {

    String title = "Graphics Template";
    Color background = Color.BLACK;
    final int CIRCLE_COUNT = 20;

    void draw(Graphics2D g2) {
        int red = 0;
        int redShift = 255 / CIRCLE_COUNT;

        int size = Math.min(getWidth(), getHeight());
        int sizeShift = size / CIRCLE_COUNT;

        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        for (int i = 0; i < CIRCLE_COUNT; ++i) {
            g2.setColor(new Color(red, 0, 0));
            g2.fillRect(x, y, size, size);

            red += redShift;
            size -= sizeShift;

            x += sizeShift / 2;
            y += sizeShift / 2;
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

