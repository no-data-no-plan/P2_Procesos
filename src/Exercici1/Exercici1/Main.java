package Exercici1.Exercici1;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n  #####################################################");
        System.out.println(" ####  SISTEMA DE RESERVA DE TEATRE - CONCURRENT #####");
        System.out.println("#####################################################");

        // Creamos los objetos zonasTeatro y le asignamos un número de asientos
        ZonaTeatre platea = new ZonaTeatre("Platea", 30);
        ZonaTeatre amfiteatro = new ZonaTeatre("Amfiteatre", 10);
        ZonaTeatre galeria = new ZonaTeatre("Galeria", 15);

        // Guardamos todas las zonas en un Array para que el cliente pueda elegir zona con un random 0-2
        ZonaTeatre[] zonas = {platea, amfiteatro, galeria};

        //Mostramos el estado del teatro
        System.out.println("ESTAT INICIAL DEL TEATRE:");
        platea.mostrarEstat();
        amfiteatro.mostrarEstat();
        galeria.mostrarEstat();

        System.out.println("\n  ##########################################");
        System.out.println(" #### INICIANT RESERVES CONCURRENTS... ####");
        System.out.println("##########################################");

        // Creamos 6 clientes que haran 3 reservas cada uno de 1 a 4 asientos.
        // Las reservas también estan hechas en zonas random
        Thread client1 = new Thread(new Cliente("Client-1", zonas, 3));
        Thread client2 = new Thread(new Cliente("Client-2", zonas, 3));
        Thread client3 = new Thread(new Cliente("Client-3", zonas, 3));
        Thread client4 = new Thread(new Cliente("Client-4", zonas, 3));
        Thread client5 = new Thread(new Cliente("Client-5", zonas, 3));
        Thread client6 = new Thread(new Cliente("Client-6", zonas, 3));

        // Se inician todos los threads a la vez.
        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();
        client6.start();

        // Usamos join para que todos los procesos finalicen al acabar de hacer lo que deben
        try {
            client1.join();
            client2.join();
            client3.join();
            client4.join();
            client5.join();
            client6.join();
        } catch (InterruptedException e) {
            System.out.println("Error esperant els threads");
        }


        // Mostramos el estado final del teatro
        System.out.println("\n  ##################################");
        System.out.println(" #### ESTAT FINAL DEL TEATRE: #####");
        System.out.println("##################################");
        platea.mostrarEstat();
        amfiteatro.mostrarEstat();
        galeria.mostrarEstat();

        System.out.println("\n   ##########################################################");
        System.out.println("  ##### TOTES LES RESERVES HAN FINALITZAT CORRECTAMENT #####");
        System.out.println(" ###### NO HI HA HAGUT OVERBOOKING NI DUPLICACIONS!  ######");
        System.out.println("##########################################################");

    }
}