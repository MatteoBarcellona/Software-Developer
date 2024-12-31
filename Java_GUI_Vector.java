
package java_gui_vector;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Java_GUI_Vector extends JFrame {
    
    JFrame frame= new JFrame();
    JButton S= new JButton("Nuovo");
    Vector<JButton> vett= new Vector<JButton>();
    
    Aggiungi S1= new Aggiungi();
    Nascondi S2= new Nascondi();
    
    int contatore;
    
    public Java_GUI_Vector() {
        frame.setLayout(null);
        
        contatore=-1;
        
        frame.add(S);
        S.setBounds(10, 10, 100, 30);
        S.addActionListener(S1);

        frame.setLocation(200, 100);
        frame.setSize(600, 600);
        
        frame.setVisible(true);
    }
    
    public static void main(String[] s) {
        new Java_GUI_Vector();
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