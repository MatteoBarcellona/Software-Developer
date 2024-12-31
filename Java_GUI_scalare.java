
package verifica_di_paiola2;
import javax.swing.*;
import java.awt.event.*;

public class Java_GUI_scalare extends JFrame {
    
    JTextField[][] T = new JTextField[10][10];
    JButton[][] B = new JButton[10][10];
    Scala S = new Scala();
    int[] posi = new int[10];
    
    JLabel L = new JLabel("Somma:");
    JTextField C = new JTextField();
    JButton R = new JButton("Reset");
    Inizia I = new Inizia();

    public Java_GUI_scalare() {
        int i, j, num;

        this.setLayout(null);

        for (j = 0; j < 10; j++) {
            for (i = 0; i < 10; i++) {
                T[i][j] = new JTextField();
                this.add(T[i][j]);
                T[i][j].setBounds((55 * i) + 20, (35 * j) + 50, 50, 30);
            }
        }

        for (j = 0; j < 10; j++) {
            for (i = 0; i < 10; i++) {
                B[i][j] = new JButton();
                this.add(B[i][j]);
                B[i][j].setBounds((55 * i) + 20, (35 * j) + 50, 50, 30);
                B[i][j].addActionListener(S);
            }
        }

        this.add(L);
        this.add(C);
        this.add(R);

        L.setBounds(20, 450, 50, 30);
        C.setBounds(75, 450, 50, 30);
        R.setBounds(200, 450, 80, 30);
        R.addActionListener(I);

        inizia();

        this.setLocation(200, 100);
        this.setSize(600, 600);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Java_GUI_scalare();
    }
    
    void inizia() {
        int i, j, num;

        for (i = 0; i < 10; i++) {
            posi[i] = -1;
        }
        C.setText("0");

        for (j = 0; j < 10; j++) {
            for (i = 0; i < 10; i++) {
                T[i][j].setEditable(false);
                T[i][j].setVisible(false);
            }
        }

        for (j = 0; j < 10; j++) {
            for (i = 0; i < 10; i++) {
                num = (int) Math.floor(Math.random() * 99) + 1;
                B[i][j].setText(String.valueOf(num));
                B[i][j].setVisible(true);
            }
        }
    }
    
    class Inizia implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            inizia();
        }
    }
    
    class Scala implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            int i, j, k;
            int num1 = 0, num2 = 0;
            String a = "";

            for (j = 0; j < 10; j++) {
                for (i = 0; i < 10; i++) {
                    if (E.getSource() == B[i][j]) {
                        if (posi[i] < 9) {
                            num1 = Integer.parseInt(B[i][j].getText());
                            num2 = Integer.parseInt(C.getText());
                            C.setText(String.valueOf(num1 + num2));

                            for (k = j; k > 0; k--) {
                                a = B[i][k - 1].getText(); 
                                B[i][k].setText(a);
                            }
                            posi[i]++;
                            B[i][posi[i]].setVisible(false);
                            T[i][posi[i]].setVisible(true);
                        }

                    }
                }
            }
        }
    }
}