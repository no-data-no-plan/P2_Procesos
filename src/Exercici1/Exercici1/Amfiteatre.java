package Exercici1.Exercici1;

import java.util.ArrayList;

public class Amfiteatre {
    public ArrayList<Asiento> asientosAmfiteatre;
    private int numAsientosMax = 10;

    public ArrayList<Asiento> getAsientosAmfiteatre() {
        return this.asientosAmfiteatre;
    }

    public void setAsientosAmfiteatre(ArrayList asientosAmfiteatre) {
        int cont = 1;

        do {
            Asiento asiento = new Asiento(cont);
            asientosAmfiteatre.add(asiento);
            ++cont;
        } while(cont != this.numAsientosMax + 1);

    }
}
