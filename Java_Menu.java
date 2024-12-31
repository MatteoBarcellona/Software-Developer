import java.util.Scanner;

public class Java_menu {

    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in);
        int cosa = -1;
        
        while(cosa!=0) {
           System.out.println("1 - voce 1");
           System.out.println("2 - voce 2");
           System.out.println("3 - voce 3");
           System.out.println("0 - fine");

           System.out.println();
           System.out.print("Cosa scegli: ");
           cosa = tastiera.nextInt();
           
           switch(cosa) {
               case 1: {
                   System.out.println("Ecco la voce 1");
                   break;
               }
               case 2: {
                   System.out.println("Ecco la voce 2");
                   break;
               }
               case 3: {
                   System.out.println("Ecco la voce 3");
                   break;
               }
           }
      
           System.out.println();
        }   
    }
}
