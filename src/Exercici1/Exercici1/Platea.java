package Exercici1.Exercici1;

import java.util.ArrayList;

public class Platea {
    private ArrayList<Asiento> asientosPlatea;
    private int numAsientosMax = 30;

    public ArrayList<Asiento> getAsientosPlatea() {
        return this.asientosPlatea;
    }

    public void setAsientosPlatea(ArrayList asientosPlatea) {
        int cont = 1;

        do {
            asientosPlatea.add(new Asiento(cont));
            ++cont;
        } while(cont != this.numAsientosMax + 1);

    }
}
