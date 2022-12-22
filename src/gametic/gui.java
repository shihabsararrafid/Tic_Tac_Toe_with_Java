package gametic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.*;
import java.util.Date;
import java.util.jar.JarEntry;

public class gui extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    Font font = new Font("", Font.BOLD, 40);
    Font font11 = new Font("", Font.BOLD, 60);
    Font font1 = new Font("", Font.BOLD, 20);
    JButton[] btn = new JButton[15];
    Color color = new Color(11, 89, 236);
    Integer k = 0;

    gui() {

        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setBackground(color);
        Title();
        MainGrid();
        clock();
    }

    public void Title() {
        // System.out.println("sdfa");
        JLabel label1 = new JLabel();
        label1.setText("Tic Tac Toe");
        // this.setLayout(new BorderLayout());
        label1.setFont(font);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label1, BorderLayout.NORTH);
    }

    public void clock() {

        JLabel label2 = new JLabel();
        label2.setText("Clock");
        label2.setFont(font1);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label2, BorderLayout.SOUTH);
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    String dateTime = new Date().toLocaleString();
                    label2.setText(dateTime);
                    // Thread.sleep(1000);
                }
            }

        };
        t.start();
    }

    public void MainGrid() {

        JPanel grid = new JPanel();

        grid.setLayout(new GridLayout(3, 3, 1, 1));
        for (int i = 0; i < 9; i++) {

            btn[i] = new JButton();
            btn[i].setFocusable(false);
            btn[i].setForeground(Color.white);
            btn[i].setBackground(Color.blue);
            btn[i].setFont(font11);
            btn[i].addActionListener(this);
            btn[i].setBorder(BorderFactory.createLineBorder(Color.white, 1));
            grid.add(btn[i]);
        }
        this.add(grid, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Do something here
        for (int i = 0; i < 9; i++) {

            if (e.getSource() == btn[i]) {
                if (k % 2 == 0)
                    btn[i].setText("O");
                else {

                    btn[i].setText("X");
                }
                k++;
                // System.out.println("Clicked");
            }
        }

    }
}
