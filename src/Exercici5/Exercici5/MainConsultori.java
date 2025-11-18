package Exercici5.Exercici5;

public class MainConsultori {
    
    public static void main(String[] args) {
        
        System.out.println("\n  ##############################################################");
        System.out.println(" ####  SISTEMA DE CONSULTORI MÈDIC CONCURRENT - EXERCICI 5 ####");
        System.out.println("##############################################################");
        
        // Creem la sala d'espera amb capacitat màxima de 4 pacients
        int CAPACITAT_SALA = 4;
        SalaEspera salaEspera = new SalaEspera(CAPACITAT_SALA);
        
        System.out.println("\nCONFIGURACIÓ DEL CONSULTORI:");
        System.out.println("Capacitat màxima de la sala: " + CAPACITAT_SALA + " pacients");
        System.out.println("El sistema implementa PRIORITAT per pacients urgents");
        
        // Mostrem l'estat inicial
        salaEspera.mostrarEstat();
        
        System.out.println("\n  ################################################");
        System.out.println(" #### INICIANT SIMULACIÓ DEL CONSULTORI... #####");
        System.out.println("################################################");
        
        // Creem el doctor que atendrà un màxim de 10 pacients
        Thread doctor = new Thread(new Doctor("Dr. Garcia", salaEspera, 10));
        
        // Creem 12 pacients: alguns urgents i altres normals
        // Els pacients arribaran en diferents moments
        Thread pacient1 = new Thread(new Pacient("Pacient-1", false, salaEspera));
        Thread pacient2 = new Thread(new Pacient("Pacient-2-URG", true, salaEspera));
        Thread pacient3 = new Thread(new Pacient("Pacient-3", false, salaEspera));
        Thread pacient4 = new Thread(new Pacient("Pacient-4", false, salaEspera));
        Thread pacient5 = new Thread(new Pacient("Pacient-5-URG", true, salaEspera));
        Thread pacient6 = new Thread(new Pacient("Pacient-6", false, salaEspera));
        Thread pacient7 = new Thread(new Pacient("Pacient-7", false, salaEspera));
        Thread pacient8 = new Thread(new Pacient("Pacient-8-URG", true, salaEspera));
        Thread pacient9 = new Thread(new Pacient("Pacient-9", false, salaEspera));
        Thread pacient10 = new Thread(new Pacient("Pacient-10", false, salaEspera));
        Thread pacient11 = new Thread(new Pacient("Pacient-11-URG", true, salaEspera));
        Thread pacient12 = new Thread(new Pacient("Pacient-12", false, salaEspera));
        
        // Iniciem el doctor primer
        doctor.start();
        
        // Donem un petit temps perquè el doctor estigui preparat
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Error en espera inicial");
        }
        
        // Iniciem els pacients amb petits retards per simular arribades progressives
        try {
            pacient1.start();
            Thread.sleep(50);
            pacient2.start();
            Thread.sleep(100);
            pacient3.start();
            pacient4.start();
            Thread.sleep(150);
            pacient5.start();
            Thread.sleep(100);
            pacient6.start();
            pacient7.start();
            Thread.sleep(200);
            pacient8.start();
            Thread.sleep(100);
            pacient9.start();
            pacient10.start();
            Thread.sleep(150);
            pacient11.start();
            Thread.sleep(100);
            pacient12.start();
        } catch (InterruptedException e) {
            System.out.println("Error en l'arribada de pacients");
        }
        
        // Esperem que tots els pacients acabin
        try {
            pacient1.join();
            pacient2.join();
            pacient3.join();
            pacient4.join();
            pacient5.join();
            pacient6.join();
            pacient7.join();
            pacient8.join();
            pacient9.join();
            pacient10.join();
            // El doctor només atendrà 10 pacients, així que els últims 2 no seran atesos
            // Esperem un temps màxim per aquests
            pacient11.join(5000);
            pacient12.join(5000);
            
            // Esperem que el doctor acabi
            doctor.join();
        } catch (InterruptedException e) {
            System.out.println("Error esperant els threads");
        }
        
        // Mostrem l'estat final
        System.out.println("\n  #####################################");
        System.out.println(" #### ESTAT FINAL DEL CONSULTORI: ####");
        System.out.println("#####################################");
        salaEspera.mostrarEstat();
        
        System.out.println("\n   ###################################################################");
        System.out.println("  ##### LA SIMULACIÓ HA FINALITZAT CORRECTAMENT #####");
        System.out.println(" ###### ELS PACIENTS URGENTS HAN TINGUT PRIORITAT! ######");
        System.out.println("###################################################################");
        
        // Forcem la finalització dels threads que puguin quedar pendents
        System.exit(0);
    }
}