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
                             " - Quantitat invàlida: " + quantitat + "EUR");
            return false;
        }
        
        double saldoAnterior = saldo;
        saldo += quantitat;
        
        System.out.println("\t\t[" + numCompte + "] INGRÉS CONFIRMAT per " + nomClient + 
                          " - Import: " + quantitat + "EUR | Saldo anterior: " + saldoAnterior + 
                          "EUR | Saldo actual: " + saldo + "EUR");
        
        return true;
    }
    
    // Metode sincronitzat per retirar diners del compte
    public synchronized boolean retirar(double quantitat, String nomClient) {
        System.out.println("\t[" + numCompte + "] " + nomClient + " vol retirar " + 
                          quantitat + "EUR. Saldo disponible: " + saldo + "EUR");
        
        // Comprovem que la quantitat sigui vàlida
        if (quantitat <= 0) {
            System.out.println("\t\t[" + numCompte + "] REINTEGRAMENT REBUTJAT per " + 
                             nomClient + " - La quantitat no es válida!");
            return false;
        }
        
        // Comprovem que hi hagi saldo suficient
        if (saldo < quantitat) {
            System.out.println("\t\t[" + numCompte + "] REINTEGRAMENT REBUTJAT per " + 
                             nomClient + " - Saldo insuficient! Saldo: " + saldo + 
                             "EUR, Vol retirar: " + quantitat + "EUR");
            return false;
        }
        
        // Fem la devoolució!
        double saldoAnterior = saldo;
        saldo -= quantitat;
        
        System.out.println("\t\t[" + numCompte + "] REINTEGRAMENT CONFIRMAT per " + nomClient + 
                          " - Import: " + quantitat + "EUR | Saldo anterior: " + saldoAnterior + 
                          "EUR | Saldo actual: " + saldo + "EUR");
        
        return true;
    }
    
    // Mètode sincronitzat per consultar el saldo 
    public synchronized double consultarSaldo(String nomClient) {
        System.out.println("\t[" + numCompte + "] " + nomClient + 
                          " consulta el saldo: " + saldo + "EUR");
        return saldo;
    }
    
    // Mètode sincronitzat per mostrar l'estat del compte
    public synchronized void mostrarEstat() {
        System.out.println("\nCompte: " + numCompte + " - Titular: " + titular + 
                          " | Saldo: " + saldo + "EUR");
    }
    
    public String getNumCompte() {
        return numCompte;
    }
    
    public String getTitular() {
        return titular;
    }
}