package progfigurageometrica;

public class ProgFiguraGeometrica {

    public static void main(String[] args) {
        
        FiguraGeometrica A1 = new FiguraGeometrica('R',4.56,3.31,0);
        FiguraGeometrica T = new FiguraGeometrica('T',7,4.89,0);
        FiguraGeometrica C = new FiguraGeometrica('C',0,0,5.55);
        FiguraGeometrica K = new FiguraGeometrica('K',6.89,4.56,9.55);
        
        System.out.println();
        System.out.println(A1.Stato());       
        System.out.println(T.Stato());       
        System.out.println(C.Stato());
        System.out.println(K.Stato());

        System.out.println();
        System.out.println("Perimetro: "+A1.getPerCirc());       
        System.out.println(T.getPerCirc());       
        System.out.println("Circonferenza: "+C.getPerCirc());
        System.out.println("Perimetro K: "+K.getPerCirc());
        
        System.out.println();
        System.out.println("Area: "+A1.getArea());       
        System.out.println("Area: "+T.getArea());       
        System.out.println("Area: "+C.getArea());
        System.out.println("Area K: "+K.getArea());
        
    }
    
}
