
package progarrayvet;

public class ProgArrayvet {

    public static void main(String[] args) {
        int i;
        float max=0,min=0;
        //metto 4 oggetti di array.
            studente s[]=new studente[4];
            //4 array studente.
            s[0]=new studente("mario",4,7,5); 
            s[1]=new studente("luca",8,2,5); 
            s[2]=new studente("matteo",8.15f,9,10); 
            s[3]=new studente("giuseppe",2,4.50f,2); 
            
            System.out.println("Dati studenti");  
      
          for(i=0;i<4;i++){
            System.out.println(s[i].getNome());
            System.out.println("Dati voti in orale: ");
            System.out.println(s[i].getvotoOrale());
            System.out.println("Dati voti in scritto:");
            System.out.println(s[i].getvotoScritto());
            System.out.println("Dati voti in pratica:");
            System.out.println(s[i].getvotoPratica());
            System.out.println("La media :");
            System.out.println(s[i].Media());
            if(s[i].Media()>max){
            max=s[i].Media();    
            }
            if(s[i].Media()<min){
            min=s[i].Media();
            }
             System.out.println("lo studente piu bravo"+ max);
             System.out.println("lo studente piu peggiore"+ min);
             System.out.println("voti azzerati " +s[i].Azzeravoti());
          }
    }
    
}
