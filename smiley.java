import javax.swing.*;
import java.awt.*;

public class smiley extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw face (circle)
        g2d.setColor(Color.GREEN);
        g2d.fillOval(50, 50, 200, 200);

        // Draw ears
        g2d.fillOval(30, 100, 40, 60);  // Left ear
        g2d.fillOval(230, 100, 40, 60); // Right ear

        // Draw eyes
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(90, 100, 30, 30); // Left eye
        g2d.fillOval(180, 100, 30, 30); // Right eye

        // Draw mouth
        g2d.setColor(Color.BLACK);
        g2d.drawArc(100, 150, 100, 50, 0, -180); // Smile arc
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Smiley Face");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        smiley panel = new smiley();
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(smiley::createAndShowGUI);
    }
}
