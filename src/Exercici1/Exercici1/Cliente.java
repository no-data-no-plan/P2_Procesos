package Exercici1.Exercici1;

import java.util.Random;

// Creamos la clase cliente con Runnable  ya que los clientes serán threads independientes
public class Cliente implements Runnable {

    private String nomCliente;
    private ZonaTeatre[] zones;
    private int numReservas;
    private Random random;

    public Cliente(String nomCliente, ZonaTeatre[] zones, int numReservas) {
        this.nomCliente = nomCliente;
        this.zones = zones;
        this.numReservas = numReservas;
        this.random = new Random();
    }

    @Override
    public void run() {

        System.out.println(nomCliente + " ha arribat al teatre!");

        // Cada cliente hace distintas reservas, de base 3
        for (int i = 0; i < numReservas; i++) {

            // Se elige de manera aleatoria alguna zona (0: Platea, 1: Amfiteatre, 2: Galeria)
            ZonaTeatre zonaTriada = zones[random.nextInt(zones.length)];

            // Cada cliente escoge de 1 a 4 asientos de manera random
            int numSeients = random.nextInt(4) + 1;

            System.out.println( nomCliente + " intenta reservar a " +
                    zonaTriada.getNomZona() + "...");


            zonaTriada.reservarAsientos(numSeients, nomCliente);

            // Damos un poco de tiempo entre reservas para ver mejor el output
            try {
                Thread.sleep(random.nextInt(100) + 50);
            } catch (InterruptedException e) {
                System.out.println(nomCliente + " ha estat interromput fent una reserva!");
            }
        }

        System.out.println("\n"+nomCliente + " ha acabat de fer les seves reserves!");
    }

}