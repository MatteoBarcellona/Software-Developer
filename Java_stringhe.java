package java_stringhe;

import java.util.Scanner;

public class Java_stringhe {

       public static void main(String args[])
   {
      Scanner in = new Scanner(System.in); //istanzia un oggetto lettore di tipo Scanner
   
      boolean z=false;
      int k=0, a=0, b=0, t=0, u=0;
      int i=0;
      int lung=0;
      int pos=0;
      char carat=' ';
      String app="";
      String bpp="";
      String sotto="";

      System.out.print("Inserisci la stringa 1: ");
      app = in.nextLine();
      System.out.print("Inserisci la stringa 2: ");
      bpp = in.nextLine();

      System.out.println("La stringa e' :"+app);
      System.out.println("\n");

      lung=app.length();
      System.out.println("La sue lunghezza e' :"+lung);
      System.out.println("\n");

      pos=2;
      carat=app.charAt(pos);
      System.out.println("Il carattere alla posizione "+pos+" e' :"+carat);
      System.out.println("\n");

      for(i=0;i<lung;i++)
      {
         carat=app.charAt(i);
         System.out.print(carat+"\n");
	  }
      System.out.println("\n");

      z=app.equals(bpp);
      if(z)
      {
         System.out.println("Stringhe "+app+" e "+bpp+" uguali");
	  }
	  else
	  {
         System.out.println("Stringhe "+app+" e "+bpp+" diverse");
      }
      System.out.println("\n");

      k=app.compareTo(bpp);
      if(k<0)
      {
         System.out.println("Stringa "+app+" minore di "+bpp);
	  }
	  else
	  {
		  if(k>0)
		  {
            System.out.println("Stringa "+app+" maggiore di "+bpp);
	      }
	      else
	      {
            System.out.println("Stringhe "+app+" e "+bpp+" uguali");
	      }
      }
      System.out.println("\n");

      a=3;
      b=7;
      sotto=app.substring(a,b);
      System.out.println("Sottostringa da "+a+" a "+b+" di "+app+" : "+sotto);
      System.out.println("\n");

      t=app.indexOf(bpp);
      if(t>=0)
      {
         System.out.println("Sottostringa "+bpp+ " trovata alla posizione "+t);
         u=app.indexOf(bpp,t+1);
         if(u>=0)
         {
            System.out.println("Sottostringa "+bpp+ " trovata anche alla posizione "+u);
	     }
      }
      else
      {
         System.out.println("Sottostringa "+bpp+ " non trovata");
	  }
      System.out.println("\n");


      System.out.println("Tutto maiuscolo "+app.toUpperCase());
      System.out.println("Tutto minuscolo "+app.toLowerCase());
      System.out.println("\n");

   }

}
