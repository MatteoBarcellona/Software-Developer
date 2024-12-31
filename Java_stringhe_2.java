package java_stringhe_2;

import java.util.Scanner;

public class Java_stringhe_2 {

    public static void main(String[] args) {
      Scanner in = new Scanner(System.in); 
      
      String a="";
      int i;
      int ls,ps;
      
      String[] vs = new String[10];
      
      for(i=0;i<10;i++) {      
         System.out.print("Inserisci la stringa n."+i+" : ");
         vs[i] = in.nextLine();
      }   
      
      ls=0;
      ps=-1;
      for(i=0;i<10;i++) {      
         if(vs[i].length()>ls) {
            ls=vs[i].length();
            ps=i;
         }
      }      
      
      System.out.println("La stringa più lunga è "+vs[ps]);
      System.out.println("ed è lunga "+ls+" caratteri");
      System.out.println("ed è alla posizione "+ps);

    }
    
}
