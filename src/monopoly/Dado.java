package monopoly;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

class Dado extends JPanel {

    Random rnd = new Random();
    int Valorcara = 1;

    public Dado(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, width, height);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(Valorcara == 1) {
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else if(Valorcara == 2) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
        } else if(Valorcara == 3) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else if(Valorcara == 4) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
        } else if(Valorcara == 5) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 5/2, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 5/2, 5, 5);
        }

    }


    public void tirarDados(){
        Valorcara = rnd.nextInt(6) + 1;
        repaint();
    }

    public int getValorcara(){
        return Valorcara;
    }

    public Dado(int xCoord, int yCoord, int width, int height, String labelString) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, width, height);

    }
}
