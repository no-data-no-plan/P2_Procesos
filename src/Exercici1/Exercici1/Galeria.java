package Exercici1.Exercici1;

import java.util.ArrayList;

public class Galeria {
    private ArrayList<Asiento> asientosGalerias;
    private int numAsientosMax = 15;

    public ArrayList<Asiento> getAsientosGalerias() {
        return this.asientosGalerias;
    }

    public void setAsientosGalerias(ArrayList asientosGalerias) {
        int cont = 1;

        do {
            asientosGalerias.add(new Asiento(cont));
            ++cont;
        } while(cont != this.numAsientosMax + 1);

    }
}
