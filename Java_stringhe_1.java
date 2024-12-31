package java_stringhe_1;

import java.util.Scanner;

public class Java_stringhe_1 {

    public static void main(String[] args) {

      Scanner in = new Scanner(System.in); 
      int t,u,i;
      String a="",b="";
      
      int[] vett = new int[20];
      int v=-1;
      
      System.out.print("Inserisci la stringa principale: ");
      a = in.nextLine();
      System.out.print("Inserisci la stringa da trovare: ");
      b = in.nextLine();
      
      t=-1;
      u=0;
      while(u>-1)
      {    
         u=a.indexOf(b,t+1);
         if(u>-1)
         {    
            System.out.println("Sottostringa "+b+ " trovata alla posizione "+u);
            v++;
            vett[v]=u;
         }   
         t=u;
      }
      System.out.println("\n");
       
      for(i=0;i<=v;i++)
      {
          System.out.println(vett[i]);
      }    
      System.out.println("\n");
    }
    
}
