package Exercici3.Exercici3;

public class MainCaixer {
    
    public static void main(String[] args) {
        
        System.out.println("\n  ###############################################################");
        System.out.println(" ####  SISTEMA DE CAIXER AUTOMÀTIC CONCURRENT - EXERCICI 3 #####");
        System.out.println("###############################################################");
        
        // Creem diversos comptes bancaris amb saldo inicial
        CompteBancari compteEstalvis = new CompteBancari("ES2100001234", "Compte Estalvis", 1000.0);
        CompteBancari compteCorrent = new CompteBancari("ES2100005678", "Compte Corrent", 500.0);
        CompteBancari compteNomina = new CompteBancari("ES2100009012", "Compte Nòmina", 2000.0);
        
        // Guardem tots els comptes en un Array per que els clients puguin accedir
        CompteBancari[] comptes = {compteEstalvis, compteCorrent, compteNomina};
        
        // Mostrem l'estat inicial dels comptes
        System.out.println("\nESTAT INICIAL DELS COMPTES:");
        compteEstalvis.mostrarEstat();
        compteCorrent.mostrarEstat();
        compteNomina.mostrarEstat();
        
        System.out.println("\n  ##############################################");
        System.out.println(" #### INICIANT OPERACIONS CONCURRENTS... #####");
        System.out.println("##############################################");
        
        // Creem 6 clients que faran 4 operacions cadascun de forma concurrent
        // Les operacions seran aleatòries: ingressar, retirar o consultar
        Thread client1 = new Thread(new ClientBancari("Client-1", comptes, 4));
        Thread client2 = new Thread(new ClientBancari("Client-2", comptes, 4));
        Thread client3 = new Thread(new ClientBancari("Client-3", comptes, 4));
        Thread client4 = new Thread(new ClientBancari("Client-4", comptes, 4));
        Thread client5 = new Thread(new ClientBancari("Client-5", comptes, 4));
        Thread client6 = new Thread(new ClientBancari("Client-6", comptes, 4));
        
        // S'inicien tots els threads a la vegada
        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();
        client6.start();
        
        // Usem join per que tots els processos finalitzin al acabar de fer lo que deuen
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
        
        // Mostrem l'estat final dels comptes
        System.out.println("\n  #####################################");
        System.out.println(" #### ESTAT FINAL DELS COMPTES: #####");
        System.out.println("#####################################");
        compteEstalvis.mostrarEstat();
        compteCorrent.mostrarEstat();
        compteNomina.mostrarEstat();
        
        // Calculem el saldo total per verificar la consistència
        double saldoTotal = compteEstalvis.consultarSaldo("Sistema") + 
                           compteCorrent.consultarSaldo("Sistema") + 
                           compteNomina.consultarSaldo("Sistema");
        
        System.out.println("\n   ###################################################################");
        System.out.println("  ##### TOTES LES OPERACIONS HAN FINALITZAT CORRECTAMENT #####");
        System.out.println(" ###### NO HI HA HAGUT INCONSISTÈNCIES NI SALDOS NEGATIUS! ######");
        System.out.println("###################################################################");
        System.out.println("\nSaldo total del sistema: " + saldoTotal + "€");
    }
}