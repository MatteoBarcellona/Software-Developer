
package java_vector_1;
import java.util.*;

class Dado {
    int valore;
    
    public Dado() {
        this.valore = (int) Math.floor( Math.random()*6)+1;
    }
    
    public int vedi() {
        return(this.valore);
    }
}

public class Java_Vector_1 {

    public static void main(String[] args) {
        
        
        Vector<Dado> vett = new Vector<Dado>();
        
        int quanti = (int) Math.floor( Math.random()*10)+1;
        
        for(int i=0;i<quanti;i++) {
           vett.addElement(new Dado());
        }
        
        System.out.println("Il vettore contiene "+vett.size()+" lanci");

        System.out.print("I lanci sono stati : ");
        for (Dado d: vett) {
           System.out.print(d.vedi()+" ");
        }
        System.out.println();
        
    }
    
}