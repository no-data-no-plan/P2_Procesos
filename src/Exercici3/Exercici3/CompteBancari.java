package Exercici3.Exercici3;

public class CompteBancari {
    
    private String numCompte;
    private double saldo;
    private String titular;
    
    public CompteBancari(String numCompte, String titular, double saldoInicial) {
        this.numCompte = numCompte;
        this.titular = titular;
        this.saldo = saldoInicial;
    }
    
    // Mètode sincronitzat per ingressar diners al compte
    public synchronized boolean ingressar(double quantitat, String nomClient) {
        if (quantitat <= 0) {
            System.out.println("\t\t[" + numCompte + "] INGRÉS REBUTJAT per " + nomClient + 
                             " - Quantitat invàlida: " + quantitat + "€");
            return false;
        }
        
        double saldoAnterior = saldo;
        saldo += quantitat;
        
        System.out.println("\t\t[" + numCompte + "] INGRÉS CONFIRMAT per " + nomClient + 
                          " - Import: " + quantitat + "€ | Saldo anterior: " + saldoAnterior + 
                          "€ | Saldo actual: " + saldo + "€");
        
        return true;
    }
    
    // Mètode sincronitzat per retirar diners del compte
    public synchronized boolean retirar(double quantitat, String nomClient) {
        System.out.println("\t[" + numCompte + "] " + nomClient + " vol retirar " + 
                          quantitat + "€. Saldo disponible: " + saldo + "€");
        
        // Comprovem que la quantitat sigui vàlida
        if (quantitat <= 0) {
            System.out.println("\t\t[" + numCompte + "] REINTEGRAMENT REBUTJAT per " + 
                             nomClient + " - Quantitat invàlida!");
            return false;
        }
        
        // Comprovem que hi hagi saldo suficient
        if (saldo < quantitat) {
            System.out.println("\t\t[" + numCompte + "] REINTEGRAMENT REBUTJAT per " + 
                             nomClient + " - Saldo insuficient! Saldo: " + saldo + 
                             "€, Vol retirar: " + quantitat + "€");
            return false;
        }
        
        // Fem el reintegrament
        double saldoAnterior = saldo;
        saldo -= quantitat;
        
        System.out.println("\t\t[" + numCompte + "] REINTEGRAMENT CONFIRMAT per " + nomClient + 
                          " - Import: " + quantitat + "€ | Saldo anterior: " + saldoAnterior + 
                          "€ | Saldo actual: " + saldo + "€");
        
        return true;
    }
    
    // Mètode sincronitzat per consultar el saldo (lectura consistent)
    public synchronized double consultarSaldo(String nomClient) {
        System.out.println("\t[" + numCompte + "] " + nomClient + 
                          " consulta el saldo: " + saldo + "€");
        return saldo;
    }
    
    // Mètode sincronitzat per mostrar l'estat del compte
    public synchronized void mostrarEstat() {
        System.out.println("\nCompte: " + numCompte + " - Titular: " + titular + 
                          " | Saldo: " + saldo + "€");
    }
    
    public String getNumCompte() {
        return numCompte;
    }
    
    public String getTitular() {
        return titular;
    }
}