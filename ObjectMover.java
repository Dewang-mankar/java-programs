
import java.awt.*;
import java.awt.event.*;

public class ObjectMover extends Frame implements ActionListener {
    Button up = new Button("Up");
    Button down = new Button("Down");
    Button right = new Button("Right");
    Button left = new Button("Left");
    Button downleft = new Button("Downleft");
    Button upright = new Button("Upright");
    Button exit = new Button("Exit");

    int x = 200;
    int y = 200;
    Object source;

    ObjectMover() {
        setSize(500, 500);
        setTitle("Move Object");
        setLayout(new FlowLayout());
        setBackground(Color.green);

        // Set preferred size for larger buttons
        Dimension buttonSize = new Dimension(100, 50);
        up.setPreferredSize(buttonSize);
        down.setPreferredSize(buttonSize);
        right.setPreferredSize(buttonSize);
        left.setPreferredSize(buttonSize);
        downleft.setPreferredSize(buttonSize);
        upright.setPreferredSize(buttonSize);
        exit.setPreferredSize(buttonSize);

        add(up);
        add(down);
        add(right);
        add(left);
        add(downleft);
        add(upright);
        add(exit);

        up.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);
        left.addActionListener(this);
        downleft.addActionListener(this);
        upright.addActionListener(this);
        exit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        source = ae.getSource();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g); // Clear previous drawings

        if (source == up) {
            y = y - 50;
        } else if (source == down) {
            y = y + 50;
        } else if (source == right) {
            x = x + 50;
        } else if (source == left) {
            x = x - 50;
        } else if (source == downleft) {
            y = y + 50;
            x = x - 50;
        }else if (source == upright) {
            y = y - 50;
            x = x + 50;
        } 
        else if (source == exit) {
            System.exit(0);
        }

        // Set the color for the filled rectangle
        g.setColor(Color.red);
        g.fillRect(x, y, 50, 50);
    }

    public static void main(String args[]) {
        ObjectMover m = new ObjectMover();
        m.setVisible(true);
    }
}


