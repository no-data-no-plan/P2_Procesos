package Exercici1.Exercici1;

import java.util.ArrayList;


// Usamos "synchronized" para evitar que dos hilos puedan acceder simultaneamente a lo mismo (race condition)
public class ZonaTeatre {

    private String nomZona;
    private ArrayList<Asiento> asientos;
    private int capacidatTotal;

    public ZonaTeatre(String nomZona, int numAsientos) {
        this.nomZona = nomZona;
        this.capacidatTotal = numAsientos;
        this.asientos = new ArrayList<>();

        // De base todos los asientos están libres y se añaden todos al arraList de asientos
        for (int i = 1; i <= numAsientos; i++) {
            asientos.add(new Asiento(i));
        }
    }

    public synchronized boolean reservarAsientos(int numAsientosAReservar, String nomCliente) {

        // Comptem quants seients lliures tenim
        int asientosLibres = contarAsientosLibres();

        System.out.println("\t[" + nomZona + "] " + nomCliente + " vol reservar " +
                numAsientosAReservar + " seients. Disponibles: " + asientosLibres);

        // Se chequea si el número de asientos que se quieren reservar estén diponíbles
        if (asientosLibres < numAsientosAReservar) {
            System.out.println("\t\t["+ nomZona +"] RESERVA REBUTJADA per " + nomCliente + " - No hi ha prou seients!");
            return false;
        }

        // Se reservan los asientos libres
        int reservados = 0;
        ArrayList<Integer> asientosReservados = new ArrayList<>();

        for (Asiento asiento : asientos) {
            if (!asiento.isOcupado() && reservados < numAsientosAReservar) {
                asiento.setOcupado(true);
                asientosReservados.add(asiento.getIdAsiento());
                reservados++;
            }
        }

        System.out.println("\t\t[" + nomZona + "] RESERVA CONFIRMADA per " + nomCliente +
                " - Seients: " + asientosReservados);

        return true;
    }

    // Métodos auxiliares, contar asientos libres totales y ver diponibilidad por zonas

    private int contarAsientosLibres() {
        int count = 0;
        for (Asiento asiento : asientos) {
            if (!asiento.isOcupado()) {
                count++;
            }
        }
        return count;
    }

    public synchronized void mostrarEstat() {
        int lliures = contarAsientosLibres();
        int ocupats = capacidatTotal - lliures;
        System.out.println("\n" + nomZona + " - Ocupats: " + ocupats + "/" +
                capacidatTotal + " | Lliures: " + lliures);
    }

    public String getNomZona() {
        return nomZona;
    }
}