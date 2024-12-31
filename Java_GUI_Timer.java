
package java_gui_timer;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class Java_GUI_Timer extends JFrame {
    
    JFrame frame= new JFrame();
    JButton G= new JButton("Start");
    JButton S= new JButton("Stop");
    JButton R= new JButton("Reset");
    Vector<JButton> vett= new Vector<JButton>();
    
    Aggiungi S1= new Aggiungi();
    Nascondi S2= new Nascondi();
    Gestisci S3= new Gestisci();
    
    Timer time= new Timer(500, S1);
    
    int contatore;
    
    public Java_GUI_Timer() {
        frame.setLayout(null);
        
        contatore=-1;
        
        frame.add(G);
        G.setBounds(10, 10, 100, 30);
        G.addActionListener(S3);
        frame.add(S);
        S.setBounds(130, 10, 100, 30);
        S.addActionListener(S3);
        frame.add(R);
        R.setBounds(250, 10, 100, 30);
        R.addActionListener(S3);

        frame.setLocation(200, 100);
        frame.setSize(600, 600);
        
        frame.setVisible(true);
    }
    
    public static void main(String[] s) {
        new Java_GUI_Timer();
    }
    
    class Gestisci implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            if(E.getSource()==G) {
              time.start();
            }
            if(E.getSource()==S){
              time.stop();
            }
            if(E.getSource()==R) {
                time.stop();
                
                for(JButton d: vett){
                  d.setVisible(false);
                }
                
                vett.clear();
                contatore=-1;
            }
        }
    }
    
    class Nascondi implements ActionListener {
        public void actionPerformed(ActionEvent E) {
            int s=-1;
            JButton p= null;
            
            for(JButton d: vett){
              if(E.getSource()==d){
                d.setVisible(false);
                p=d;
              }
            }
            
            vett.remove(p);
            s=vett.size();
            System.out.println("Quanti: "+s);
        }
    }
    
    class Aggiungi implements ActionListener {
        public void actionPerformed(ActionEvent E) {
           int x, y, s;
           
           x= (int)Math.floor( Math.random()*500)+10;
           y= (int)Math.floor( Math.random()*400)+120;
           vett.addElement(new JButton());
           s=vett.size();
           
           frame.add(vett.get(s-1));
           vett.get(s-1).setBounds(x, y, 50, 30);
           contatore++;
           vett.get(s-1).setText(String.valueOf(contatore));
           vett.get(s-1).addActionListener(S2);
           
           s=vett.size();
           System.out.println("Quanti: "+s);
        }   
    }
}