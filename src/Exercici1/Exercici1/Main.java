package Exercici1.Exercici1;

public class Main {
    public static void main(String[] args) {
        Amfiteatre Amfi = new Amfiteatre();
        Amfi.setAsientosAmfiteatre(Amfi.asientosAmfiteatre);

        for(Asiento asiento : Amfi.getAsientosAmfiteatre()) {
            System.out.println(asiento.getIdAsiento());
        }

    }
}
