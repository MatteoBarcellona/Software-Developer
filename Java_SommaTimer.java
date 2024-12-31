
package java_sommatimer;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

public class Java_SommaTimer extends JFrame {
    int a, b, c;
    int quante;

    Calcola S = new Calcola();
    Nuovo N = new Nuovo();

    Timer time = new Timer(3000, S);

    Button BA = new Button("avvia");
    Label C = new Label("");
    TextField R = new TextField();

    public Java_SommaTimer() {
        setLayout(null);

        this.quante = 3;
        this.a = 0;
        this.b = 0;
        this.c = 0;

        this.add(BA);
        BA.setBounds(30, 100, 50, 30);
        BA.addActionListener(N);
        BA.setVisible(true);

        this.add(C);
        C.setBounds(30, 50, 100, 30);
        C.setText("");

        this.add(R);
        R.setBounds(150, 50, 70, 30);
        R.setText("");
        R.setVisible(false);

        setTitle("SommaButton");
        setLocation(200, 100);
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] s) {
        new Java_SommaTimer();
    }

    class Calcola implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            int r = 0, s = 0;

            time.stop();

            try {
                r = Integer.parseInt(R.getText());
            } catch (Exception E) {
                r = 0;
            }

            s = a + b;
            R.setVisible(false);
            if (r == s) {
                C.setText("giusto");
            } else {
                C.setText("sbagliato");
            }

            BA.setVisible(true);

            quante--;
            if (quante == 0) {
                BA.setVisible(false);
                C.setText(C.getText() + " - fine");
            }
        }
    }

    class Nuovo implements ActionListener {

        public void actionPerformed(ActionEvent E) {

            int num;

            num = (int) Math.floor(Math.random() * 99) + 1;
            a = num;
            num = (int) Math.floor(Math.random() * 99) + 1;
            b = num;
            C.setText(a + " + " + b + " = ");
            R.setVisible(true);
            R.setText("");

            BA.setVisible(false);
            time.start();

        }
    }

}