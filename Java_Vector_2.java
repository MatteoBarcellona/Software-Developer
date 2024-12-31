
package java_vector_2;
import java.util.*;

class Imballo implements Comparable<Imballo> {
    int peso;
    
    public Imballo() {
        this.peso = (int) Math.floor( Math.random()*45)+6;
    }
    
    public int vedi() {
        return(this.peso);
    }
    
    public int compareTo(Imballo altroImballo)
    {
        return this.vedi() - altroImballo.vedi();
    }
}

public class Java_Vector_2 {

    public static void main(String[] args) {

        Vector<Imballo> vett = new Vector<Imballo>();
        
        int quanti = (int) Math.floor( Math.random()*10)+1;
        
        for(int i=0;i<quanti;i++) {
           vett.addElement(new Imballo());
        }
        
        System.out.println("Nel container ci sono "+vett.size()+" imballi");

        System.out.print("I loro pesi sono : ");
        for (Imballo ii: vett) {
           System.out.print(ii.vedi()+" ");
        }
        System.out.println();

        Collections.sort(vett);
        
        System.out.print("I loro pesi ordinati sono : ");
        for (Imballo ii: vett) {
           System.out.print(ii.vedi()+" ");
        }
        System.out.println();

    }
    
}