package gametic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    boolean[] check = new boolean[10];
    JOptionPane optionPane = new JOptionPane();
    int[] winPlayer = new int[10];
    int[][] winComb = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 0, 3, 6 }, { 3, 6, 9 },
            { 1, 5, 9 }, { 3, 5, 7 } };

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
        label1.setText("Player 0 vs Player 1");
        // this.setLayout(new BorderLayout());
        label1.setFont(font);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label1, BorderLayout.NORTH);
    }

    public void clock() {

        JLabel label2 = new JLabel();
        label2.setText("Clock");
        label2.setFont(font1);
        label2.setBackground(Color.MAGENTA);
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

            btn[i].setBackground(new Color(35, 89, 236));
            btn[i].setFont(font11);
            btn[i].addActionListener(this);
            btn[i].setBorder(BorderFactory.createLineBorder(Color.white, 1));
            grid.add(btn[i]);
        }
        for (int i = 0; i < 10; i++) {
            check[i] = false;
        }
        this.add(grid, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // changing the color and text of grids
        for (int i = 0; i < 9; i++) {

            if (e.getSource() == btn[i] && !check[i]) {
                if (k % 2 == 0) {

                    btn[i].setForeground(Color.white);
                    btn[i].setText("O");
                    winPlayer[i + 1] = 1;
                } else {
                    btn[i].setForeground(Color.red);
                    btn[i].setText("X");
                    winPlayer[i + 1] = 2;
                }
                check[i] = true;
                // btn[i].setEnabled(false);
                k++;
                // System.out.println("Clicked");
            } else if (e.getSource() == btn[i] && check[i]) {
                optionPane.showOptionDialog(null, "Postion already Occupied", "Error", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, null, null);
            }
        }
        // winning logic
        if (k >= 5) {
            boolean ok = false;
            for (int i = 0; i < 8; i++) {
                if (winPlayer[winComb[i][0]] == 2 && winPlayer[winComb[i][1]] == 2 && winPlayer[winComb[i][2]] == 2) {
                    System.out.println("Player 2");
                    ok = true;
                    optionPane.showMessageDialog(null, "player 2 win");
                    // this.dispose();
                } else if (winPlayer[winComb[i][0]] == 1 && winPlayer[winComb[i][1]] == 1
                        && winPlayer[winComb[i][2]] == 1) {
                    ok = true;
                    System.out.println("player 1");
                    optionPane.showMessageDialog(null, "player 1 win");
                    // this.dispose();
                }
            }
            if (ok) {

                int output = optionPane.showConfirmDialog(null, "Replay the game?");
                System.out.println(output);
                if (output != 0) {
                    this.dispose();
                } else if (output == 0) {
                    this.dispose();
                    new gui();
                }

            }
        }

    }
}
