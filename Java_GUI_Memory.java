
package java_gui_memory;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class Java_GUI_Memory extends JFrame {
    
    JButton R= new JButton("Reset");
    JLabel L= new JLabel("Conta:");
    JTextField C= new JTextField("");
    
    JButton[][] B= new JButton[5][4];
    JLabel[][] D= new JLabel[5][4];
    
    GestBottone S1= new GestBottone();
    
    int conta;
    int oldnum, old_r, old_c, turno;
    
    int[] cifre= new int[20];
    
    public Java_GUI_Memory() {
        int i = -1, j = -1;
        int n = 0, k = 0, w = 0, n1 = 0, n2 = 0;
        
        this.setLayout(null);
        
        this.add(R);
        this.add(L);
        this.add(C);
        
        R.setBounds(10, 300, 70, 30);
        R.addActionListener(S1);
        L.setBounds(150, 300, 50, 30);
        C.setBounds(200, 300, 50, 30);
        C.setEditable(false);
        
        w = 0;
        for (k = 0; k < 10; k++) {   
            cifre[w] = k;
            cifre[w + 1] = k;
            w = w + 2;
        }
        for (k = 0; k < 100; k++) {   
            n1 = (int) Math.floor(Math.random() * 20);
            n2 = (int) Math.floor(Math.random() * 20);
            n = cifre[n1];
            cifre[n1] = cifre[n2];
            cifre[n2] = n;
        }
        
        w = 0;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 4; j++) {
                B[i][j] = new JButton();
                this.add(B[i][j]);
                B[i][j].setBounds(10 + (i * 42), 10 + (j * 42), 40, 40);
                B[i][j].setVisible(true);
                B[i][j].addActionListener(S1);

                D[i][j] = new JLabel();
                this.add(D[i][j]);
                D[i][j].setBounds(10 + (i * 42), 10 + (j * 42), 40, 40);
                D[i][j].setBackground(Color.yellow);
                D[i][j].setOpaque(true);
                D[i][j].setHorizontalAlignment(JLabel.CENTER);
                D[i][j].setVisible(false);

                n = cifre[w];
                w++;
                D[i][j].setText(String.valueOf(n));
            }
        }
        
        conta = 0;
        C.setText(String.valueOf(conta));
        
        oldnum = -1;
        old_r = -1;
        old_c = -1;
        turno = 1;
        
        this.setLocation(200, 100);
        this.setSize(400, 400);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Java_GUI_Memory();
    }
    
    class GestBottone implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            int i = -1, j = -1;
            int nn = 0, newnum = -1;
            int n, n1, n2, nr, w, k;
            
            for (i = 0; i < 5; i++) {
                for (j = 0; j < 4; j++) {
                    if (E.getSource() == B[i][j]) {
                        if (turno == 1) {
                            B[i][j].setVisible(false);
                            D[i][j].setVisible(true);
                            oldnum = Integer.parseInt(D[i][j].getText()); 
                            old_r = j;
                            old_c = i;
                            turno = 2;
                        }
                        else {
                            B[i][j].setVisible(false);
                            D[i][j].setVisible(true);
                            newnum = Integer.parseInt(D[i][j].getText());
                            if (newnum != oldnum) {
                                B[i][j].setVisible(true);
                                D[i][j].setVisible(false);
                                B[old_c][old_r].setVisible(true);
                                D[old_c][old_r].setVisible(false);
                            }
                            else {
                                conta++;
                                C.setText(String.valueOf(conta));
                            }
                            oldnum = -1;
                            old_r = -1;
                            old_c = -1;
                            turno = 1;
                        }
                        
                    }
                }
            }
            
            if (E.getSource() == R) {
                for (k = 0; k < 100; k++) {
                    n1 = (int) Math.floor(Math.random() * 20);
                    n2 = (int) Math.floor(Math.random() * 20);
                    n = cifre[n1];
                    cifre[n1] = cifre[n2];
                    cifre[n2] = n;
                }
                
                w = 0;
                for (i = 0; i < 5; i++) {
                    for (j = 0; j < 4; j++) {
                        B[i][j].setVisible(true);
                        D[i][j].setVisible(false);
                        nr = cifre[w];
                        w++;
                        D[i][j].setText(String.valueOf(nr));
                    }
                }
                conta = 0;
                C.setText(String.valueOf(conta));
            }
        }
        
    }
    
}