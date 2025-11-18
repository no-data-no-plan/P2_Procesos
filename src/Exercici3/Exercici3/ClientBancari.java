package Exercici3.Exercici3;

import java.util.Random;

// Creem la classe client amb Runnable ja que els clients seran threads independents
public class ClientBancari implements Runnable {
    
    private String nomClient;
    private CompteBancari[] comptes;
    private int numOperacions;
    private Random random;
    
    public ClientBancari(String nomClient, CompteBancari[] comptes, int numOperacions) {
        this.nomClient = nomClient;
        this.comptes = comptes;
        this.numOperacions = numOperacions;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        
        System.out.println(nomClient + " ha arribat al caixer automàtic!");
        
        // Cada client fa diverses operacions bancàries
        for (int i = 0; i < numOperacions; i++) {
            
            // S'escull de manera aleatòria algun compte
            CompteBancari compteTriat = comptes[random.nextInt(comptes.length)];
            
            // Escollim tipus d'operació aleatòriament (0: Ingressar, 1: Retirar, 2: Consultar)
            int tipusOperacio = random.nextInt(3);
            
            System.out.println(nomClient + " intenta fer una operació al compte " + 
                             compteTriat.getNumCompte() + "...");
            
            switch (tipusOperacio) {
                case 0:
                    // Ingressar: quantitat aleatòria entre 50 i 500 euros
                    double quantitatIngressar = 50 + random.nextDouble() * 450;
                    quantitatIngressar = Math.round(quantitatIngressar * 100.0) / 100.0; // Arrodonir a 2 decimals
                    compteTriat.ingressar(quantitatIngressar, nomClient);
                    break;
                    
                case 1:
                    // Retirar: quantitat aleatòria entre 20 i 300 euros
                    double quantitatRetirar = 20 + random.nextDouble() * 280;
                    quantitatRetirar = Math.round(quantitatRetirar * 100.0) / 100.0; // Arrodonir a 2 decimals
                    compteTriat.retirar(quantitatRetirar, nomClient);
                    break;
                    
                case 2:
                    // Consultar saldo
                    compteTriat.consultarSaldo(nomClient);
                    break;
            }
            
            // Donem un poc de temps entre operacions per veure millor l'output
            try {
                Thread.sleep(random.nextInt(100) + 50);
            } catch (InterruptedException e) {
                System.out.println(nomClient + " ha estat interromput fent una operació!");
            }
        }
        
        System.out.println("\n" + nomClient + " ha acabat de fer les seves operacions!");
    }
}